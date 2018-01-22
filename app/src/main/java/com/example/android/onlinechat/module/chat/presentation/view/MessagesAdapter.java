package com.example.android.onlinechat.module.chat.presentation.view;

import com.example.android.onlinechat.R;
import com.example.android.onlinechat.module.chat.domain.model.MessageEntity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author mshulga
 * @since 22.01.18
 */

public class MessagesAdapter extends FirebaseRecyclerAdapter<MessageEntity, MessagesAdapter.MessageViewHolder> {

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageUser;
        TextView messageText;

        MessageViewHolder(View v) {
            super(v);
            messageUser = itemView.findViewById(R.id.message_user);
            messageText = itemView.findViewById(R.id.message_text);
        }
    }

    public MessagesAdapter(FirebaseRecyclerOptions<MessageEntity> options) {
        super(options);
    }

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
}
