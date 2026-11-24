package com.rudel.reborn;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import com.google.firebase.ai.FirebaseAI;
import com.google.firebase.ai.GenerativeModel;
import com.google.firebase.ai.java.GenerativeModelFutures;
import com.google.firebase.ai.type.GenerativeBackend;
import com.google.firebase.ai.type.Content;
import com.google.firebase.ai.type.GenerateContentResponse;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.FutureCallback;

public class RudraAIActivity extends Activity {
 private LinearLayout messages; private ScrollView scroll; private EditText input; private Button send; private String history=""; private int generation=0;
 private int dp(int n){return Math.round(n*getResources().getDisplayMetrics().density);}
 private GradientDrawable bg(int color){GradientDrawable d=new GradientDrawable();d.setColor(color);d.setCornerRadius(dp(18));return d;}
 private void bubble(String text,boolean user){TextView t=new TextView(this);t.setText(text);t.setTextColor(Color.WHITE);t.setTextSize(16);t.setTextIsSelectable(true);t.setPadding(dp(16),dp(13),dp(16),dp(13));t.setBackground(bg(user?0xff364b9b:0xff202b40));LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2);p.setMargins(user?dp(35):0,dp(6),user?0:dp(20),dp(6));messages.addView(t,p);scroll.post(()->scroll.fullScroll(ScrollView.FOCUS_DOWN));}
 @Override public void onCreate(Bundle b){super.onCreate(b);if(com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser()==null){finish();return;}LinearLayout root=new LinearLayout(this);root.setOrientation(1);root.setPadding(dp(16),dp(16),dp(16),dp(12));root.setBackgroundColor(0xff0b1020);setContentView(root);TextView title=new TextView(this);title.setText("✦  Rudra AI");title.setTextSize(30);title.setTextColor(Color.WHITE);root.addView(title);TextView info=new TextView(this);info.setText("Online assistant • AI can make mistakes. Messages are sent to Google AI; do not share private information.");info.setTextColor(0xffb7c5df);root.addView(info);Button clear=new Button(this);clear.setText("New conversation");root.addView(clear);scroll=new ScrollView(this);messages=new LinearLayout(this);messages.setOrientation(1);scroll.addView(messages);root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));input=new EditText(this);input.setHint("Ask in Hindi or English");input.setTextColor(Color.WHITE);input.setHintTextColor(0xffaab8ce);input.setMaxLines(4);root.addView(input);send=new Button(this);send.setText("Send message");root.addView(send);clear.setOnClickListener(v->{generation++;history="";messages.removeAllViews();send.setEnabled(true);});send.setOnClickListener(v->ask());bubble("Ask a question, explore a concept or plan a project.",false);}
 private void ask(){String q=input.getText().toString().trim();if(q.isEmpty()||q.length()>2000)return;input.setText("");bubble(q,true);send.setEnabled(false);final int token=++generation;try{GenerativeModel model=FirebaseAI.getInstance(GenerativeBackend.googleAI()).generativeModel("gemini-2.5-flash");GenerativeModelFutures client=GenerativeModelFutures.from(model);Content prompt=new Content.Builder().addText("You are Rudra, a safe educational assistant for a general audience including teenagers. Reply in the user's language. Avoid dangerous instructions and protect privacy. Conversation:\n"+history+"\nUser: "+q).build();ListenableFuture<GenerateContentResponse> future=client.generateContent(prompt);Futures.addCallback(future,new FutureCallback<GenerateContentResponse>(){public void onSuccess(GenerateContentResponse r){if(isFinishing()||token!=generation)return;String a=r.getText();if(a==null||a.isEmpty())a="No response available.";bubble(a,false);history+=("\nUser: "+q+"\nAssistant: "+a);if(history.length()>10000)history=history.substring(history.length()-10000);send.setEnabled(true);}public void onFailure(Throwable e){if(isFinishing()||token!=generation)return;android.util.Log.e("RUDEL_AI", "AI request failed", e);
bubble("AI Error: " + e.getClass().getSimpleName(),false);send.setEnabled(true);}},getMainExecutor());}catch(Exception e){bubble("AI setup unavailable.",false);send.setEnabled(true);}}
}
