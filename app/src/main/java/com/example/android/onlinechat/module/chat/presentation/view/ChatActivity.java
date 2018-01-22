package com.example.android.onlinechat.module.chat.presentation.view;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.android.onlinechat.BaseActivity;
import com.example.android.onlinechat.ContentView;
import com.example.android.onlinechat.R;
import com.example.android.onlinechat.module.chat.domain.model.MessageEntity;
import com.example.android.onlinechat.module.login.presentation.view.LoginActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author mshulga
 * @since 18.01.18
 */
@ContentView(R.layout.activity_chat)
public class ChatActivity extends BaseActivity {

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageUser;
        TextView messageText;

        public MessageViewHolder(View v) {
            super(v);
            messageUser = itemView.findViewById(R.id.message_user);
            messageText = itemView.findViewById(R.id.message_text);
        }
    }

    public static final String ANONYMOUS = "anonymous";
    public static final String MESSAGES_CHILD = "messages";
    @BindView(R.id.send_button)
    Button mSendMessageButton;
    @BindView(R.id.message_edit_text)
    EditText mMessageEditText;
    @BindView(R.id.message_list)
    RecyclerView mMessageList;
    private String mCurrentUserNickname = ANONYMOUS;
    private FirebaseRecyclerAdapter adapter;

    @OnClick(R.id.send_button)
    public void sendMessageButtonClick() {
        String messageBody = mMessageEditText.getText().toString();
        FirebaseDatabase.getInstance()
            .getReference()
            .child(MESSAGES_CHILD)
            .push()
            .setValue(new MessageEntity(mCurrentUserNickname, messageBody));
        mMessageEditText.setText("");
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
                FirebaseAuth.getInstance().signOut();
                mCurrentUserNickname = ANONYMOUS;
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCurrentUserNickname = extras.getString("nick");
        }

        SnapshotParser<MessageEntity> parser = dataSnapshot -> {
            MessageEntity friendlyMessage = dataSnapshot.getValue(MessageEntity.class);
            if (friendlyMessage != null) {
                friendlyMessage.setUid(dataSnapshot.getKey());
            }
            return friendlyMessage;
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);

        DatabaseReference messagesRef = FirebaseDatabase.getInstance().getReference().child(MESSAGES_CHILD);

        FirebaseRecyclerOptions<MessageEntity> options =
            new FirebaseRecyclerOptions.Builder<MessageEntity>()
                .setQuery(messagesRef, parser)
                .build();

        adapter = new FirebaseRecyclerAdapter<MessageEntity, MessageViewHolder>(options) {
            @Override
            public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
                return new MessageViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(MessageViewHolder holder, int position, MessageEntity model) {
                holder.messageText.setText(model.getText());
                holder.messageUser.setText(model.getUserNickname());
            }
        };

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

        mMessageList.setLayoutManager(layoutManager);
        mMessageList.setAdapter(adapter);

    }
}
