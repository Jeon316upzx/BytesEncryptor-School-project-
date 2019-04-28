package com.example.ifeanyi.bytesencryptor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by IFEANYI on 1/12/2017.
 */
public class DecryptFragment extends Fragment {
    EditText e1,t1;
    Button b1,b2;
    TextView t2;

    SecretKeySpec skp;

    byte [] en_by;
    byte [] en_RSA;

    String mySeed,MyStringValue;

    FloatingActionButton fab1,fab2;



    Key myPrivateKey;
    Key mypublickey;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.decrypt_fragment,container,false);


        e1 = (EditText) v.findViewById(R.id.Text_to_be_encrpted);
        b1 = (Button) v.findViewById(R.id.EncryptButton);
        b2 = (Button) v.findViewById(R.id.DecryptButton);

        t1 = (EditText) v.findViewById(R.id.EncrptedText);
        t2 = (TextView) v.findViewById(R.id.decrptedText);

        fab1 = (FloatingActionButton) v.findViewById(R.id.myShare);
        fab2 = (FloatingActionButton) v.findViewById(R.id.myClear);



        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(t1.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(),"Nothing has been encrypted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent in = new Intent(Intent.ACTION_SEND);
                    in.setType("text/plain");
                    in.putExtra(Intent.EXTRA_TEXT, t1.getText().toString());
                    in.putExtra(Intent.EXTRA_SUBJECT,t1.getText().toString());
                    startActivity(Intent.createChooser(in, "Share with Friends"));
                }



            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("");
                t2.setText("");
                e1.setText("");

            }
        });





        mySeed ="SeedmySeed";




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //encryption with AES
                if(e1.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Empty Textbox", Toast.LENGTH_SHORT).show();
                    t1.setText("");
                    t2.setText("");

                }
                else
                {

                    final Dialog d = new Dialog(getContext());
                    d.setContentView(R.layout.encrpt_dialog);
                    d.setTitle("Select encryption algorithm");
                    d.show();

                    Button b11 = (Button) d.findViewById(R.id.EN_AES);
                    Button b12 = (Button) d.findViewById(R.id.EN_RSA);
                    Button b13 = (Button) d.findViewById(R.id.EN_SHA);
                    b11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            MyStringValue = e1.getText().toString();
                            skp = null;

                            try {
                                SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
                                sr.setSeed(mySeed.getBytes());
                                KeyGenerator keyG = KeyGenerator.getInstance("AES");
                                keyG.init(128, sr);
                                skp = new SecretKeySpec(keyG.generateKey().getEncoded(), "AES");

                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }


                            en_by = null;
                            try {
                                Cipher cipher = Cipher.getInstance("AES");
                                cipher.init(Cipher.ENCRYPT_MODE, skp);
                                en_by = cipher.doFinal(MyStringValue.getBytes());
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (NoSuchPaddingException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            } catch (BadPaddingException e) {
                                e.printStackTrace();
                            } catch (IllegalBlockSizeException e) {
                                e.printStackTrace();
                            }


                            t1.setText(Base64.encodeToString(en_by, Base64.DEFAULT));

                            d.cancel();
                        }
                    });


                    b12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //encryption with RSA

                            mypublickey=null;
                            myPrivateKey =null;

                            try {
                                KeyPairGenerator kpg =KeyPairGenerator.getInstance("RSA");
                                kpg.initialize(1024);
                                KeyPair kp = kpg.genKeyPair();
                                myPrivateKey = kp.getPrivate();
                                mypublickey = kp.getPublic();
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }


                            en_RSA = null;
                            try {
                                Cipher cipher = Cipher.getInstance("RSA");
                                cipher.init(Cipher.ENCRYPT_MODE,myPrivateKey);
                                en_RSA = cipher.doFinal(MyStringValue.getBytes());
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (NoSuchPaddingException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            } catch (BadPaddingException e) {
                                e.printStackTrace();
                            } catch (IllegalBlockSizeException e) {
                                e.printStackTrace();
                            }

                            t1.setText(Base64.encodeToString(en_RSA,Base64.DEFAULT));


                        }
                    });

                    b13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {




                        }
                    });




                }
            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {





                if(t1.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(),"Empty textbox",Toast.LENGTH_SHORT).show();
                    t1.setText("");
                }
                else
                {

                    final Dialog dg = new Dialog(getContext());
                    dg.setContentView(R.layout.decrypt_dialog);
                    dg.setTitle("select decryption Algorithm");
                    dg.show();

                    Button D11 = (Button) dg.findViewById(R.id.DN_AES);
                    Button D12 = (Button) dg.findViewById(R.id.DN_RSA);
                    final Button D13 = (Button) dg.findViewById(R.id.DN_SHA);


                    D11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            byte [] decod = null;

                            try {
                                Cipher cip = Cipher.getInstance("AES");
                                cip.init(Cipher.DECRYPT_MODE,skp);
                                decod = cip.doFinal(en_by);
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (NoSuchPaddingException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            } catch (BadPaddingException e) {
                                e.printStackTrace();
                            } catch (IllegalBlockSizeException e) {
                                e.printStackTrace();
                            }


                            t2.setText(new String(decod));


                            dg.cancel();

                        }
                    });


                    D12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            byte[] decodedBytes = null;
                            try {
                                Cipher c = Cipher.getInstance("RSA");
                                c.init(Cipher.DECRYPT_MODE, mypublickey);
                                decodedBytes = c.doFinal(en_RSA);
                            } catch (Exception e) {

                            }


                            t2.setText(new String(decodedBytes));


                        }
                    });



                    D13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {





                        }
                    });



                }

            }
        });


        return v;
    }

}
