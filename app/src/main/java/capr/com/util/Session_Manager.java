package capr.com.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;

import capr.com.beans.User_DTO;

/**
 * Created by Gantz on 23/06/14.
 */
public class Session_Manager {

    private static final String PREFERENCE_NAME = "glup_preferences";
    private int PRIVATE_MODE = 0;

    /*
    USUARIO DATA SESSION - JSON
     */
    public static final String USER_CODE = "user_code";
    public static final String USER_NAME = "user_name";
    public static final String USER_LOGIN = "user_login";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Session_Manager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean isLogin() {
        return preferences.getBoolean(USER_LOGIN, false);
    }

    public void crearSession(User_DTO user_dto) throws JSONException {
        editor.putBoolean(USER_LOGIN, true);
        editor.putString(USER_NAME,user_dto.getNombre_empresa());
        editor.putString(USER_CODE, user_dto.getCodigo_empresa().toString());
        editor.commit();
    }

    public String getName() {
        return preferences.getString(USER_NAME,"GLUP");
    }
    public String getCode() {
        return preferences.getString(USER_CODE,"GLUP");
    }

    public void cerrarSession() {
        editor.putBoolean(USER_LOGIN, false);
        editor.putString(USER_NAME, null);
        editor.putInt(USER_CODE, -1);
        editor.commit();
    }
}