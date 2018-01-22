package com.example.android.onlinechat.module.chat.presentation.view;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.android.onlinechat.BaseActivity;
import com.example.android.onlinechat.ContentView;
import com.example.android.onlinechat.R;
import com.example.android.onlinechat.di.Injector;
import com.example.android.onlinechat.module.chat.di.ChatComponent;
import com.example.android.onlinechat.module.chat.di.ChatModule;
import com.example.android.onlinechat.module.chat.domain.model.MessageEntity;
import com.example.android.onlinechat.module.chat.presentation.presenter.ChatPresenter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.android.onlinechat.Constants.ANONYMOUS;
import static com.example.android.onlinechat.Constants.MESSAGES_DB_SCHEME;
import static com.example.android.onlinechat.Constants.NICKNAME_EXTRAS_KEY;

/**
 * @author mshulga
 * @since 18.01.18
 */
@ContentView(R.layout.activity_chat)
public class ChatActivity extends BaseActivity implements ChatView {

    @BindView(R.id.send_button)
    Button mSendMessageButton;
    @BindView(R.id.message_edit_text)
    EditText mMessageEditText;
    @BindView(R.id.message_list)
    RecyclerView mMessageList;

    @Inject
    ChatPresenter mChatPresenter;

    private String mCurrentUserNickname = ANONYMOUS;
    private FirebaseRecyclerAdapter adapter;


    @OnClick(R.id.send_button)
    public void sendMessageButtonClick() {
        String messageBody = mMessageEditText.getText().toString();
        mChatPresenter.onSendMessageButtonClick(mCurrentUserNickname, messageBody);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                mChatPresenter.onSignOutClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void clearMessageEditText() {
        mMessageEditText.setText("");
    }

    @Override
    public void destroySelf() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChatPresenter.onViewDestroyed();
        mChatPresenter = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCurrentUserNickname = extras.getString(NICKNAME_EXTRAS_KEY);
        }
        initMessagesList();
        injectBeans();
    }

    private void injectBeans() {
        ChatModule chatModule = new ChatModule(this);
        ChatComponent chatComponent = Injector.getInstance().getAppComponent().plus(chatModule);
        chatComponent.inject(this);
    }

    private void initMessagesList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        initMessagesAdapter(layoutManager);
        mMessageList.setLayoutManager(layoutManager);
        mMessageList.setAdapter(adapter);
    }

    private void initMessagesAdapter(LinearLayoutManager layoutManager) {

        SnapshotParser<MessageEntity> parser = dataSnapshot -> {
            MessageEntity friendlyMessage = dataSnapshot.getValue(MessageEntity.class);
            if (friendlyMessage != null) {
                friendlyMessage.setUid(dataSnapshot.getKey());
            }
            return friendlyMessage;
        };

        DatabaseReference messagesRef = FirebaseDatabase.getInstance().getReference().child(MESSAGES_DB_SCHEME);

        FirebaseRecyclerOptions<MessageEntity> options =
            new FirebaseRecyclerOptions.Builder<MessageEntity>()
                .setQuery(messagesRef, parser)
                .build();

        adapter = new MessagesAdapter(options);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = adapter.getItemCount();
                int lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 ||
                    (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    mMessageList.scrollToPosition(positionStart);
                }
            }
        });
    }
}
