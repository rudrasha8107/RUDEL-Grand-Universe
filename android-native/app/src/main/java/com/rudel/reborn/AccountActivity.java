package com.rudel.reborn;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.*;
import android.text.InputType;
import com.google.firebase.auth.*;
import com.google.firebase.firestore.*;
import java.util.HashMap;

public class AccountActivity extends Activity {
 private LinearLayout panel; private FirebaseAuth auth; private TextView status; private boolean busy;
 @Override public void onCreate(Bundle b){super.onCreate(b); auth=FirebaseAuth.getInstance(); show();}
 private int dp(int n){return (int)(n*getResources().getDisplayMetrics().density);}
 private void text(String s,int size){TextView t=new TextView(this);t.setText(s);t.setTextColor(Color.WHITE);t.setTextSize(size);panel.addView(t);}
 private EditText field(String hint,int type){EditText e=new EditText(this);e.setHint(hint);e.setSingleLine(true);e.setInputType(type);e.setTextColor(Color.WHITE);e.setHintTextColor(0xffa6b8ca);panel.addView(e);return e;}
 private void button(String title,Runnable run){Button b=new Button(this);b.setText(title);b.setAllCaps(false);b.setOnClickListener(v->{if(!busy)run.run();});
b.setTextColor(Color.WHITE);
b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xff4659d9));
LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, dp(52));
lp.setMargins(0,dp(8),0,dp(4));
panel.addView(b,lp);
}
 private void msg(String s){status.setText(s);}
 private void show(){busy=false; ScrollView scroll=new ScrollView(this);panel=new LinearLayout(this);panel.setOrientation(1);panel.setPadding(dp(24),dp(28),dp(24),dp(24));panel.setBackgroundColor(0xff080f20);scroll.setFillViewport(true);scroll.addView(panel);setContentView(scroll);text("RUDEL GRAND UNIVERSE",30);text("Your world. Your identity.",16);status=new TextView(this);status.setTextColor(0xff80e5da);panel.addView(status);
 FirebaseUser u=auth.getCurrentUser(); if(u==null){EditText email=field("Email",33),pass=field("Password (6+ characters)",129);
 button("Sign in",()->{if(email.length()==0||pass.length()==0){msg("Enter email and password.");return;}busy=true;auth.signInWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString()).addOnCompleteListener(t->{busy=false;if(t.isSuccessful())show();else msg("Sign in failed. Check credentials and connection.");});});
 button("Create account",()->{if(email.length()==0||pass.length()<6){msg("Enter email and at least 6 password characters.");return;}busy=true;auth.createUserWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString()).addOnCompleteListener(t->{busy=false;if(t.isSuccessful())show();else msg("Could not create account. Check email and password.");});});
 button("Reset password",()->{if(email.length()==0){msg("Enter email.");return;}busy=true;auth.sendPasswordResetEmail(email.getText().toString().trim()).addOnCompleteListener(t->{busy=false;msg(t.isSuccessful()?"Check your email for reset instructions.":"Unable to request reset.");});});
 }else{text(u.getEmail(),17);text(u.isEmailVerified()?"Email verified":"Email not verified",14);button("Send verification email",()->u.sendEmailVerification().addOnCompleteListener(t->msg(t.isSuccessful()?"Check your inbox.":"Could not send email.")));button("Refresh verification",()->u.reload().addOnCompleteListener(t->{if(t.isSuccessful())show();else msg("Could not refresh.");}));
 EditText name=field("Display name",1); DocumentReference doc=FirebaseFirestore.getInstance().collection("users").document(u.getUid());doc.get().addOnSuccessListener(d->{String n=d.getString("displayName");if(n!=null&&name.length()==0)name.setText(n);});
 button("Save private profile",()->{String n=name.getText().toString().trim();if(n.isEmpty()||n.length()>60){msg("Enter a name of 1–60 characters.");return;}HashMap<String,Object> data=new HashMap<>();data.put("displayName",n);busy=true;doc.set(data,SetOptions.merge()).addOnCompleteListener(t->{busy=false;msg(t.isSuccessful()?"Profile saved online.":"Profile not saved. Check rules and connection.");});});EditText cloudNote=field("Private cloud note",1);doc.get().addOnSuccessListener(d->{String n=d.getString("privateNote");if(n!=null&&cloudNote.length()==0)cloudNote.setText(n);});
 button("Save cloud note",()->{String n=cloudNote.getText().toString();if(n.length()>2000){msg("Maximum 2000 characters.");return;}busy=true;HashMap<String,Object> data=new HashMap<>();data.put("privateNote",n);doc.set(data,SetOptions.merge()).addOnCompleteListener(t->{busy=false;msg(t.isSuccessful()?"Private note saved online.":"Could not save note.");});});
 button("Reload cloud note",()->doc.get(Source.SERVER).addOnCompleteListener(t->{if(t.isSuccessful()){String n=t.getResult().getString("privateNote");cloudNote.setText(n==null?"":n);msg("Note loaded from server.");}else msg("Unable to reach server.");}));
 button("Sign out",()->{auth.signOut();show();});}
 if(auth.getCurrentUser()!=null){
        button("Enter RUDEL",()->{
            startActivity(new android.content.Intent(this,MainActivity.class));
            finish();
        });
    }}
}
