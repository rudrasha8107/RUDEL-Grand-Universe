package com.rudel.reborn;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.graphics.Color;
import com.google.firebase.auth.*;
import com.google.firebase.firestore.*;
import java.util.*;
public class SocialActivity extends Activity {
 LinearLayout root,feed; TextView status; ListenerRegistration listener;
 FirebaseFirestore db=FirebaseFirestore.getInstance();
 int dp(int n){return (int)(n*getResources().getDisplayMetrics().density);}
 TextView label(String s,int size,LinearLayout parent){TextView t=new TextView(this);t.setText(s);t.setTextSize(size);t.setTextColor(Color.WHITE);t.setPadding(dp(12),dp(10),dp(12),dp(10));parent.addView(t);return t;}
 @Override public void onCreate(Bundle state){super.onCreate(state);FirebaseUser u=FirebaseAuth.getInstance().getCurrentUser();if(u==null){finish();return;}
 ScrollView scroll=new ScrollView(this);root=new LinearLayout(this);root.setOrientation(1);root.setPadding(dp(16),dp(16),dp(16),dp(16));root.setBackgroundColor(0xff101327);scroll.setFillViewport(true);scroll.addView(root);setContentView(scroll);
 label("RUDEL • Community",26,root);label("Share text with signed-in members. Do not share personal information.",14,root);
 EditText input=new EditText(this);input.setHint("Write a moment…");input.setTextColor(Color.WHITE);input.setHintTextColor(0xffaab4cc);root.addView(input);Button send=new Button(this);send.setText("Publish moment");root.addView(send);status=label("",14,root);feed=new LinearLayout(this);feed.setOrientation(1);root.addView(feed);
 send.setOnClickListener(v->{String s=input.getText().toString().trim();if(s.isEmpty()||s.length()>500){status.setText("Enter 1–500 characters.");return;}send.setEnabled(false);Map<String,Object> m=new HashMap<>();m.put("uid",u.getUid());m.put("text",s);m.put("createdAt",FieldValue.serverTimestamp());db.collection("moments").add(m).addOnCompleteListener(this,t->{send.setEnabled(true);status.setText(t.isSuccessful()?"Published":"Publish failed");if(t.isSuccessful())input.setText("");});});
 listener=db.collection("moments").orderBy("createdAt",Query.Direction.DESCENDING).limit(50).addSnapshotListener(this,(snap,error)->{if(error!=null){status.setText("Feed unavailable");return;}feed.removeAllViews();if(snap==null)return;for(DocumentSnapshot d:snap.getDocuments()){LinearLayout card=new LinearLayout(this);card.setOrientation(1);card.setBackgroundColor(0xff252b46);LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,-2);lp.setMargins(0,dp(8),0,dp(8));feed.addView(card,lp);label(d.getString("text"),18,card);if(u.getUid().equals(d.getString("uid"))){Button del=new Button(this);del.setText("Delete my moment");card.addView(del);del.setOnClickListener(v->d.getReference().delete().addOnFailureListener(e->status.setText("Delete failed")));}}});
 }
 @Override protected void onDestroy(){if(listener!=null)listener.remove();super.onDestroy();}
}
