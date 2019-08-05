package com.example.hackathonapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ForumPage extends AppCompatActivity {
    Button forumPage_forum_back;
    ImageView forum_add;
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_page);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Forum");
        mDatabase.keepSynced(true);
        forumPage_forum_back = findViewById(R.id.forum_back);
        forum_add = findViewById(R.id.forum_add);
        mBlogList = findViewById(R.id.bloglist);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        forumPage_forum_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ForumPage.this, Fun_page.class));
                finish();
            }
        });
        forum_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForumPage.this, AddForum.class));
                finish();
            }
        });


    }

     @Override
        protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<forum,BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<forum, BlogViewHolder>(

                forum.class,R.layout.blog_row,BlogViewHolder.class,mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, forum forum, int i) {

                viewHolder.setTitle(forum.getTitle());
                viewHolder.setAuthor(forum.getAuthor());
                viewHolder.setDescription(forum.getDescription());
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title){
            TextView post_title =(TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setAuthor(String author){
            TextView post_author = (TextView) mView.findViewById(R.id.post_author);
            post_author.setText(author);
        }
        public void setDescription(String description){
            TextView post_description = mView.findViewById(R.id.post_description);
            post_description.setText(description);
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ForumPage.this, Fun_page.class));
        finish();
    }
}
