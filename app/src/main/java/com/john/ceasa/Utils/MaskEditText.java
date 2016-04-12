package com.john.ceasa.Utils;

/**
 * Created by paulo on 20/01/15.
 */

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaskEditText implements TextWatcher{

      private static MaskEditText instance;

      private String mMask;
      private EditText mEditText;

      private boolean isUpdating;
      private String old = "";
      public static  String errorMessage = "";

      public MaskEditText(String mask, EditText editText) {
            mMask = mask;
            mEditText = editText;
      }

      public static String unmask(String s) {
            return s.replaceAll("[.]", "").replaceAll("[-]", "")
                    .replaceAll("[/]", "").replaceAll("[(]", "")
                    .replaceAll("[)]", "");
      }
      public static String mask(String format, String text){
            String maskedText="";
            int i =0;
            for (char m : format.toCharArray()) {
                  if (m != '#') {
                        maskedText += m;
                        continue;
                  }
                  try {
                        maskedText += text.charAt(i);
                  } catch (Exception e) {
                        break;
                  }
                  i++;
            }
            return maskedText;
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
            String str = unmask(s.toString());
            String mascara = "";

            if (isUpdating) {
                  old = str;
                  isUpdating = false;
                  return;
            }

            if(str.length() > old.length())
                  mascara = mask(mMask,str);
            else
                  mascara = s.toString();

            isUpdating = true;

            mEditText.setText(mascara);
            mEditText.setSelection(mascara.length());
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }


      @Override
      public void afterTextChanged(Editable s) {

      }



}