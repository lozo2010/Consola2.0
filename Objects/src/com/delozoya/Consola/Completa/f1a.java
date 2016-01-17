package com.delozoya.Consola.Completa;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class f1a extends Activity implements B4AActivity{
	public static f1a mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.delozoya.Consola.Completa", "com.delozoya.Consola.Completa.f1a");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (f1a).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, true))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.delozoya.Consola.Completa", "com.delozoya.Consola.Completa.f1a");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.delozoya.Consola.Completa.f1a", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (f1a) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (f1a) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return f1a.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (f1a) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (f1a) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static int _flag = 0;
public static String _strmodelo = "";
public static anywheresoftware.b4a.objects.UsbSerial _usb1 = null;
public static String _strspinner = "";
public static String _strfichero = "";
public static anywheresoftware.b4a.randomaccessfile.AsyncStreams _astreams1 = null;
public com.delozoya.Consola.Completa.clsexplorer _dlgfileexpl = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btok = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etfichero = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etgiro = null;
public anywheresoftware.b4a.objects.EditTextWrapper _ettiempo = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvdatos = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spconfig = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spservo = null;
public static boolean _ex = false;
public anywheresoftware.b4a.objects.Timer _timer1 = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog _dial = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _sptiempo = null;
public anywheresoftware.b4a.objects.LabelWrapper _label8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label9 = null;
public static String _nombre_fichero = "";
public static String _config = "";
public anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2 _di = null;
public static int _contador = 0;
public static byte[] _buffer_tx = null;
public static byte _valor_d = (byte)0;
public static boolean _first_time = false;
public anywheresoftware.b4a.objects.ButtonWrapper _btescribirmemoria = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbrx = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bt124 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _bt1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btascii49 = null;
public static boolean _flag_d = false;
public anywheresoftware.b4a.objects.ButtonWrapper _bttestavr = null;
public static int _x = 0;
public static boolean _flag_rx = false;
public anywheresoftware.b4a.objects.ButtonWrapper _btleer = null;
public anywheresoftware.b4a.objects.collections.List _listaconfiguracion = null;
public anywheresoftware.b4a.objects.collections.List _lista1 = null;
public static int _contador_lineas = 0;
public static String _servo = "";
public static String _giro = "";
public static String _tiempo = "";
public static String _tipo_tiempo = "";
public static String _servo_lv = "";
public static String _tiempo_lv = "";
public static String _tipo_tiempo_lv = "";
public static String _giro_lv = "";
public static String _tiempo_aux = "";
public static String _tipo_tiempo_aux = "";
public static String _tipo_tiempo_aux1 = "";
public static String _giro_aux = "";
public static String _servo_aux = "";
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbnumeroservo = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbmodelo = null;
public anywheresoftware.b4a.objects.LabelWrapper _label10 = null;
public static String _posicion_mod = "";
public static String _tiempo_ante_lv = "";
public static String _tiempo_post_lv = "";
public static String _servo_ante_lv = "";
public static String _servo_post_lv = "";
public static String _tipo_ante_lv = "";
public static String _tipo_post_lv = "";
public static boolean _flag_multi = false;
public static boolean _flag_button2 = false;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public static int _valor_inicial = 0;
public static int _valor_final = 0;
public static int _numero_suma = 0;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _bc = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _bc1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel4 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spconfig1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btcomenzar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btsalir = null;
public anywheresoftware.b4a.objects.IME _ime1 = null;
public static int _tiempo_minimo = 0;
public static int _tiempo_actual = 0;
public static boolean _tiempo_repite = false;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btgrabar = null;
public static String _servo_aux_nombre = "";
public anywheresoftware.b4a.objects.LabelWrapper _lbservotxt = null;
public anywheresoftware.b4a.objects.Timer _tim = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbled = null;
public static String _nombre_leido = "";
public static boolean _flag_lectura = false;
public static int _t = 0;
public static boolean _flag_inicio_usb = false;
public com.delozoya.Consola.Completa.main _main = null;
public com.delozoya.Consola.Completa.f1c _f1c = null;
public com.delozoya.Consola.Completa.f1b _f1b = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _abrir_fichero_leido(String _nombre) throws Exception{
String _aux = "";
 //BA.debugLineNum = 3122;BA.debugLine="Sub Abrir_Fichero_leido (nombre As String)";
 //BA.debugLineNum = 3123;BA.debugLine="lbModelo.Text=nombre";
mostCurrent._lbmodelo.setText((Object)(_nombre));
 //BA.debugLineNum = 3124;BA.debugLine="strfichero=nombre";
_strfichero = _nombre;
 //BA.debugLineNum = 3125;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 3126;BA.debugLine="Dim aux As String";
_aux = "";
 //BA.debugLineNum = 3128;BA.debugLine="If nombre.Contains(\"PRUEBA\") Then";
if (_nombre.contains("PRUEBA")) { 
 //BA.debugLineNum = 3129;BA.debugLine="strspinner=\"PRUEBA\"";
_strspinner = "PRUEBA";
 };
 //BA.debugLineNum = 3131;BA.debugLine="If nombre.Contains(\"1ª.HORA\") Then";
if (_nombre.contains("1ª.HORA")) { 
 //BA.debugLineNum = 3132;BA.debugLine="strspinner=\"1ª.HORA\"";
_strspinner = "1ª.HORA";
 };
 //BA.debugLineNum = 3134;BA.debugLine="If nombre.Contains(\"V.ALTO\") Then";
if (_nombre.contains("V.ALTO")) { 
 //BA.debugLineNum = 3135;BA.debugLine="strspinner=\"V.ALTO\"";
_strspinner = "V.ALTO";
 };
 //BA.debugLineNum = 3137;BA.debugLine="If nombre.Contains(\"V.MEDIO\") Then";
if (_nombre.contains("V.MEDIO")) { 
 //BA.debugLineNum = 3138;BA.debugLine="strspinner=\"V.MEDIO\"";
_strspinner = "V.MEDIO";
 };
 //BA.debugLineNum = 3140;BA.debugLine="If nombre.Contains(\"CALMA\") Then";
if (_nombre.contains("CALMA")) { 
 //BA.debugLineNum = 3141;BA.debugLine="strspinner=\"CALMA\"";
_strspinner = "CALMA";
 };
 //BA.debugLineNum = 3143;BA.debugLine="If nombre.Contains(\"TERMICA\") Then";
if (_nombre.contains("TERMICA")) { 
 //BA.debugLineNum = 3144;BA.debugLine="strspinner=\"TERMICA\"";
_strspinner = "TERMICA";
 };
 //BA.debugLineNum = 3146;BA.debugLine="AbrirFichero";
_abrirfichero();
 //BA.debugLineNum = 3147;BA.debugLine="End Sub";
return "";
}
public static String  _abrirfichero() throws Exception{
int _numpos = 0;
int _i = 0;
 //BA.debugLineNum = 2857;BA.debugLine="Sub AbrirFichero";
 //BA.debugLineNum = 2858;BA.debugLine="Dim numpos As Int";
_numpos = 0;
 //BA.debugLineNum = 2859;BA.debugLine="Try";
try { //BA.debugLineNum = 2865;BA.debugLine="Log(\"abro fichero\")";
anywheresoftware.b4a.keywords.Common.Log("abro fichero");
 //BA.debugLineNum = 2866;BA.debugLine="NOMBRE_FICHERO=strfichero";
mostCurrent._nombre_fichero = _strfichero;
 //BA.debugLineNum = 2867;BA.debugLine="etFichero.Text=NOMBRE_FICHERO";
mostCurrent._etfichero.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 2868;BA.debugLine="numpos=NOMBRE_FICHERO.IndexOf(\" \")";
_numpos = mostCurrent._nombre_fichero.indexOf(" ");
 //BA.debugLineNum = 2869;BA.debugLine="ToastMessageShow(NOMBRE_FICHERO,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(mostCurrent._nombre_fichero,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2875;BA.debugLine="ToastMessageShow(numpos,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.NumberToString(_numpos),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2876;BA.debugLine="If strspinner=\"PRUEBA\" Then";
if ((_strspinner).equals("PRUEBA")) { 
 //BA.debugLineNum = 2877;BA.debugLine="spconfig.SelectedIndex=1";
mostCurrent._spconfig.setSelectedIndex((int) (1));
 };
 //BA.debugLineNum = 2879;BA.debugLine="If strspinner=\"1ª.HORA\" Then";
if ((_strspinner).equals("1ª.HORA")) { 
 //BA.debugLineNum = 2880;BA.debugLine="spconfig.SelectedIndex=2";
mostCurrent._spconfig.setSelectedIndex((int) (2));
 };
 //BA.debugLineNum = 2882;BA.debugLine="If strspinner=\"V.ALTO\" Then";
if ((_strspinner).equals("V.ALTO")) { 
 //BA.debugLineNum = 2883;BA.debugLine="spconfig.SelectedIndex=3";
mostCurrent._spconfig.setSelectedIndex((int) (3));
 };
 //BA.debugLineNum = 2885;BA.debugLine="If strspinner=\"V.MEDIO\" Then";
if ((_strspinner).equals("V.MEDIO")) { 
 //BA.debugLineNum = 2886;BA.debugLine="spconfig.SelectedIndex=4";
mostCurrent._spconfig.setSelectedIndex((int) (4));
 };
 //BA.debugLineNum = 2888;BA.debugLine="If strspinner=\"CALMA\" Then";
if ((_strspinner).equals("CALMA")) { 
 //BA.debugLineNum = 2889;BA.debugLine="spconfig.SelectedIndex=5";
mostCurrent._spconfig.setSelectedIndex((int) (5));
 };
 //BA.debugLineNum = 2891;BA.debugLine="If strspinner=\"TERMICA\" Then";
if ((_strspinner).equals("TERMICA")) { 
 //BA.debugLineNum = 2892;BA.debugLine="spconfig.SelectedIndex=6";
mostCurrent._spconfig.setSelectedIndex((int) (6));
 };
 //BA.debugLineNum = 2897;BA.debugLine="If File.Exists(File.DirRootExternal & \"/Consola/";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 2900;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 2901;BA.debugLine="strModelo=\"Modelo_F1A_\"&NOMBRE_FICHERO";
_strmodelo = "Modelo_F1A_"+mostCurrent._nombre_fichero;
 //BA.debugLineNum = 2902;BA.debugLine="lista1=File.ReadList(File.DirRootExternal & \"/C";
mostCurrent._lista1 = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero);
 //BA.debugLineNum = 2903;BA.debugLine="listaconfiguracion=File.ReadList(File.DirRootEx";
mostCurrent._listaconfiguracion = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A/configuracion","Modelo_F1A_"+mostCurrent._nombre_fichero);
 //BA.debugLineNum = 2906;BA.debugLine="If listaconfiguracion.Get(0)=\"0\" Then";
if ((mostCurrent._listaconfiguracion.Get((int) (0))).equals((Object)("0"))) { 
 //BA.debugLineNum = 2907;BA.debugLine="cbled.Checked=False";
mostCurrent._cbled.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 2910;BA.debugLine="If listaconfiguracion.Get(0)=\"1\" Then";
if ((mostCurrent._listaconfiguracion.Get((int) (0))).equals((Object)("1"))) { 
 //BA.debugLineNum = 2911;BA.debugLine="cbled.Checked=True";
mostCurrent._cbled.setChecked(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 2940;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2941;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (0)))+"  REMOLQUE");
 //BA.debugLineNum = 2942;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2943;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (1)))+"  REMOLQUE");
 //BA.debugLineNum = 2944;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2945;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (2)))+"  REMOLQUE");
 //BA.debugLineNum = 2946;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2947;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (3)))+"  REMOLQUE");
 //BA.debugLineNum = 2948;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2949;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (4)))+"  REMOLQUE");
 //BA.debugLineNum = 2951;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2952;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (5)))+"  RDT");
 //BA.debugLineNum = 2953;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2954;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (6)))+"  RDT");
 //BA.debugLineNum = 2955;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2956;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (7)))+"  RDT");
 //BA.debugLineNum = 2957;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2958;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (8)))+"  RDT");
 //BA.debugLineNum = 2959;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2960;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (9)))+"  RDT");
 //BA.debugLineNum = 2962;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2963;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (10)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2964;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2965;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (11)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2966;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2967;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (12)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2968;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2969;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (13)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2970;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2971;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (14)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2973;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2974;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (15)))+"  PRE-START");
 //BA.debugLineNum = 2975;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2976;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (16)))+"  PRE-START");
 //BA.debugLineNum = 2977;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2978;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (17)))+"  PRE-START");
 //BA.debugLineNum = 2979;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2980;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (18)))+"  PRE-START");
 //BA.debugLineNum = 2981;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2982;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (19)))+"  PRE-START");
 //BA.debugLineNum = 2984;BA.debugLine="Log(\"Tamaño Lista \"&lista1.Size)";
anywheresoftware.b4a.keywords.Common.Log("Tamaño Lista "+BA.NumberToString(mostCurrent._lista1.getSize()));
 //BA.debugLineNum = 2986;BA.debugLine="For i=20 To lista1.Size-1 Step 4";
{
final int step1903 = (int) (4);
final int limit1903 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (20); (step1903 > 0 && _i <= limit1903) || (step1903 < 0 && _i >= limit1903); _i = ((int)(0 + _i + step1903))) {
 //BA.debugLineNum = 2987;BA.debugLine="tiempo_aux=lista1.Get(i)";
mostCurrent._tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get(_i));
 //BA.debugLineNum = 2988;BA.debugLine="tiempo_minimo=lista1.get(i)";
_tiempo_minimo = (int)(BA.ObjectToNumber(mostCurrent._lista1.Get(_i)));
 //BA.debugLineNum = 2989;BA.debugLine="Log(\"Tiempo actual \" &tiempo_minimo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo actual "+BA.NumberToString(_tiempo_minimo));
 //BA.debugLineNum = 2990;BA.debugLine="tipo_tiempo_aux=lista1.Get(i+1)";
mostCurrent._tipo_tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+1)));
 //BA.debugLineNum = 2991;BA.debugLine="servo_aux=lista1.Get(i+2)";
mostCurrent._servo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+2)));
 //BA.debugLineNum = 2993;BA.debugLine="If tipo_tiempo_aux=0 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 2994;BA.debugLine="tiempo_minimo=tiempo_minimo";
_tiempo_minimo = _tiempo_minimo;
 };
 //BA.debugLineNum = 2998;BA.debugLine="If tipo_tiempo_aux=1 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2999;BA.debugLine="tiempo_minimo=tiempo_minimo*10";
_tiempo_minimo = (int) (_tiempo_minimo*10);
 };
 //BA.debugLineNum = 3003;BA.debugLine="If tipo_tiempo_aux=2 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 3004;BA.debugLine="tiempo_minimo=tiempo_minimo*600";
_tiempo_minimo = (int) (_tiempo_minimo*600);
 };
 //BA.debugLineNum = 3009;BA.debugLine="If servo_aux=1 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 3010;BA.debugLine="servo_aux_nombre=\"INC.STABILO 1\"";
mostCurrent._servo_aux_nombre = "INC.STABILO 1";
 };
 //BA.debugLineNum = 3012;BA.debugLine="If servo_aux=2 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 3013;BA.debugLine="servo_aux_nombre=\"DIRECCION   2\"";
mostCurrent._servo_aux_nombre = "DIRECCION   2";
 };
 //BA.debugLineNum = 3015;BA.debugLine="If servo_aux=3 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 3016;BA.debugLine="servo_aux_nombre=\"C.INCI.ALAS 3\"";
mostCurrent._servo_aux_nombre = "C.INCI.ALAS 3";
 };
 //BA.debugLineNum = 3018;BA.debugLine="If servo_aux=4 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 3019;BA.debugLine="servo_aux_nombre=\"FLAPPER     4\"";
mostCurrent._servo_aux_nombre = "FLAPPER     4";
 };
 //BA.debugLineNum = 3021;BA.debugLine="If servo_aux=5 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 3022;BA.debugLine="servo_aux_nombre=\"GANCHO/FLAP 5\"";
mostCurrent._servo_aux_nombre = "GANCHO/FLAP 5";
 };
 //BA.debugLineNum = 3024;BA.debugLine="giro_aux=lista1.Get(i+3)";
mostCurrent._giro_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+3)));
 //BA.debugLineNum = 3025;BA.debugLine="If tipo_tiempo_aux=\"0\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("0")) { 
 //BA.debugLineNum = 3026;BA.debugLine="tipo_tiempo_aux=\"DECIMAS\"";
mostCurrent._tipo_tiempo_aux = "DECIMAS";
 };
 //BA.debugLineNum = 3029;BA.debugLine="If tipo_tiempo_aux=\"1\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("1")) { 
 //BA.debugLineNum = 3030;BA.debugLine="tipo_tiempo_aux=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo_aux = "SEGUNDOS";
 };
 //BA.debugLineNum = 3033;BA.debugLine="If tipo_tiempo_aux=\"2\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("2")) { 
 //BA.debugLineNum = 3034;BA.debugLine="tipo_tiempo_aux=\"MINUTOS\"";
mostCurrent._tipo_tiempo_aux = "MINUTOS";
 };
 //BA.debugLineNum = 3036;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 3038;BA.debugLine="lvDatos.AddSingleLine(servo_aux_nombre&\"   G";
mostCurrent._lvdatos.AddSingleLine(mostCurrent._servo_aux_nombre+"   Giro: "+mostCurrent._giro_aux+"    Tiempo: "+mostCurrent._tiempo_aux+"  Tipo tiempo: "+mostCurrent._tipo_tiempo_aux);
 }
};
 //BA.debugLineNum = 3041;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3042;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3043;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3044;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3045;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3046;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3047;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3048;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3049;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3050;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 3051;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 3053;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3054;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3055;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3056;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3057;BA.debugLine="contador_lineas=22";
_contador_lineas = (int) (22);
 //BA.debugLineNum = 3058;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3059;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3060;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3061;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3062;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3063;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3064;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3065;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3066;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3067;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 3071;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 } 
       catch (Exception e1975) {
			processBA.setLastException(e1975); //BA.debugLineNum = 3077;BA.debugLine="If File.Exists(File.DirRootExternal & \"/Consola/";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 3078;BA.debugLine="lista1.Clear";
mostCurrent._lista1.Clear();
 //BA.debugLineNum = 3081;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 3082;BA.debugLine="strModelo=\"Modelo_F1A_\"&NOMBRE_FICHERO";
_strmodelo = "Modelo_F1A_"+mostCurrent._nombre_fichero;
 //BA.debugLineNum = 3084;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3085;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3086;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3087;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3088;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3089;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3090;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3091;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3092;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3093;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3094;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3095;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3097;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 //BA.debugLineNum = 3098;BA.debugLine="lbNumeroServo.Text=\"STABILO\"";
mostCurrent._lbnumeroservo.setText((Object)("STABILO"));
 //BA.debugLineNum = 3099;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 //BA.debugLineNum = 3100;BA.debugLine="btOK.Enabled=True";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3101;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3102;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3103;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3104;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3105;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3106;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3107;BA.debugLine="btOK.Visible=True";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3109;BA.debugLine="tiempo_actual=0";
_tiempo_actual = (int) (0);
 //BA.debugLineNum = 3110;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 3111;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 };
 //BA.debugLineNum = 3114;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 };
 //BA.debugLineNum = 3120;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 157;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 158;BA.debugLine="Try";
try { //BA.debugLineNum = 159;BA.debugLine="Activity.LoadLayout(\"f1c1\")";
mostCurrent._activity.LoadLayout("f1c1",mostCurrent.activityBA);
 //BA.debugLineNum = 160;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 161;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 162;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 163;BA.debugLine="timer1.Initialize(\"Timer1\",1000)";
mostCurrent._timer1.Initialize(processBA,"Timer1",(long) (1000));
 //BA.debugLineNum = 164;BA.debugLine="timer1.Enabled=True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 166;BA.debugLine="tim.Initialize(\"tim\",500)";
mostCurrent._tim.Initialize(processBA,"tim",(long) (500));
 //BA.debugLineNum = 167;BA.debugLine="tim.Enabled=True";
mostCurrent._tim.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 168;BA.debugLine="ime1.SetCustomFilter(etTiempo,etTiempo.INPUT_TYPE";
mostCurrent._ime1.SetCustomFilter((android.widget.EditText)(mostCurrent._ettiempo.getObject()),mostCurrent._ettiempo.INPUT_TYPE_DECIMAL_NUMBERS,"0123456789");
 //BA.debugLineNum = 169;BA.debugLine="ime1.SetCustomFilter(etGiro,etGiro.INPUT_TYPE_DEC";
mostCurrent._ime1.SetCustomFilter((android.widget.EditText)(mostCurrent._etgiro.getObject()),mostCurrent._etgiro.INPUT_TYPE_DECIMAL_NUMBERS,"0123456789");
 //BA.debugLineNum = 175;BA.debugLine="File.MakeDir(File.DirRootExternal,\"/Consola/F1A\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"/Consola/F1A");
 //BA.debugLineNum = 176;BA.debugLine="File.MakeDir(File.DirRootExternal & \"/Consola/F1A\"";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","configuracion");
 //BA.debugLineNum = 178;BA.debugLine="BC1.Initialize(Colors.White,5)";
mostCurrent._bc1.Initialize(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (5));
 //BA.debugLineNum = 179;BA.debugLine="ime1.Initialize(\"\")";
mostCurrent._ime1.Initialize("");
 //BA.debugLineNum = 180;BA.debugLine="InitPanel(Panel1,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel1,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 181;BA.debugLine="InitPanel(Panel2,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel2,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 182;BA.debugLine="InitPanel(Panel3,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel3,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 183;BA.debugLine="InitPanel(Panel4,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel4,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 184;BA.debugLine="Label10.Text=\"F1-A\"";
mostCurrent._label10.setText((Object)("F1-A"));
 //BA.debugLineNum = 186;BA.debugLine="BC.Initialize(Colors.Blue,5)";
mostCurrent._bc.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Blue,(int) (5));
 //BA.debugLineNum = 189;BA.debugLine="spServo.Add(\"PULSE AQUI\")";
mostCurrent._spservo.Add("PULSE AQUI");
 //BA.debugLineNum = 190;BA.debugLine="spServo.Add(\"STABILO\")";
mostCurrent._spservo.Add("STABILO");
 //BA.debugLineNum = 191;BA.debugLine="spServo.Add(\"DIRECCION\")";
mostCurrent._spservo.Add("DIRECCION");
 //BA.debugLineNum = 192;BA.debugLine="spServo.Add(\"C.I.ALAS\")";
mostCurrent._spservo.Add("C.I.ALAS");
 //BA.debugLineNum = 193;BA.debugLine="spServo.Add(\"FLAPPER\")";
mostCurrent._spservo.Add("FLAPPER");
 //BA.debugLineNum = 194;BA.debugLine="spServo.Add(\"GANCHO/FLAPS 5\")";
mostCurrent._spservo.Add("GANCHO/FLAPS 5");
 //BA.debugLineNum = 197;BA.debugLine="spTiempo.Add(\"PULSE AQUI\")";
mostCurrent._sptiempo.Add("PULSE AQUI");
 //BA.debugLineNum = 198;BA.debugLine="spTiempo.Add(\"DECIMAS\")";
mostCurrent._sptiempo.Add("DECIMAS");
 //BA.debugLineNum = 199;BA.debugLine="spTiempo.Add(\"SEGUNDOS\")";
mostCurrent._sptiempo.Add("SEGUNDOS");
 //BA.debugLineNum = 200;BA.debugLine="spTiempo.Add(\"MINUTOS\")";
mostCurrent._sptiempo.Add("MINUTOS");
 //BA.debugLineNum = 202;BA.debugLine="spconfig.Add(\"PULSE AQUI\")";
mostCurrent._spconfig.Add("PULSE AQUI");
 //BA.debugLineNum = 203;BA.debugLine="spconfig.Add(\"PRUEBA\")";
mostCurrent._spconfig.Add("PRUEBA");
 //BA.debugLineNum = 204;BA.debugLine="spconfig.Add(\"1ª.HORA\")";
mostCurrent._spconfig.Add("1ª.HORA");
 //BA.debugLineNum = 205;BA.debugLine="spconfig.Add(\"V.ALTO\")";
mostCurrent._spconfig.Add("V.ALTO");
 //BA.debugLineNum = 206;BA.debugLine="spconfig.Add(\"V.MEDIO\")";
mostCurrent._spconfig.Add("V.MEDIO");
 //BA.debugLineNum = 207;BA.debugLine="spconfig.Add(\"CALMA\")";
mostCurrent._spconfig.Add("CALMA");
 //BA.debugLineNum = 208;BA.debugLine="spconfig.Add(\"TERMICA\")";
mostCurrent._spconfig.Add("TERMICA");
 //BA.debugLineNum = 209;BA.debugLine="spconfig.TextSize=23";
mostCurrent._spconfig.setTextSize((float) (23));
 //BA.debugLineNum = 211;BA.debugLine="spServo.DropdownBackgroundColor=Colors.White";
mostCurrent._spservo.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 212;BA.debugLine="spTiempo.DropdownBackgroundColor=Colors.White";
mostCurrent._sptiempo.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 213;BA.debugLine="spconfig.DropdownBackgroundColor=Colors.White";
mostCurrent._spconfig.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 215;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 216;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 217;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 218;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 219;BA.debugLine="Panel2.Visible=False";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 220;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 221;BA.debugLine="Label5.Visible=False";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 222;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 224;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 225;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 226;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 227;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 228;BA.debugLine="Label8.Text=\"Remolque\"";
mostCurrent._label8.setText((Object)("Remolque"));
 //BA.debugLineNum = 229;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 230;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 231;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 //BA.debugLineNum = 232;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 //BA.debugLineNum = 233;BA.debugLine="lista1.Initialize";
mostCurrent._lista1.Initialize();
 //BA.debugLineNum = 234;BA.debugLine="listaconfiguracion.Initialize2(Array As Stri";
mostCurrent._listaconfiguracion.Initialize2(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"0","nombre"}));
 //BA.debugLineNum = 235;BA.debugLine="listaconfiguracion.Set(1,NOMBRE_FICHERO)";
mostCurrent._listaconfiguracion.Set((int) (1),(Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 236;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consol";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A/configuracion","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._listaconfiguracion);
 //BA.debugLineNum = 240;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 241;BA.debugLine="spconfig.Enabled=False";
mostCurrent._spconfig.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 242;BA.debugLine="lbModelo.Text=\"PULSE AQUI\"";
mostCurrent._lbmodelo.setText((Object)("PULSE AQUI"));
 //BA.debugLineNum = 243;BA.debugLine="openusb";
_openusb();
 } 
       catch (Exception e171) {
			processBA.setLastException(e171); };
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 3149;BA.debugLine="Sub Activity_KeyPress(KeyCode As Int) As Boolean";
 //BA.debugLineNum = 3151;BA.debugLine="If dlgFileExpl.IsInitialized Then";
if (mostCurrent._dlgfileexpl.IsInitialized()) { 
 //BA.debugLineNum = 3152;BA.debugLine="If dlgFileExpl.IsActive Then Return True";
if (mostCurrent._dlgfileexpl._isactive()) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
 };
 //BA.debugLineNum = 3154;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 3155;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 264;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 265;BA.debugLine="usb1.Close";
_usb1.Close();
 //BA.debugLineNum = 266;BA.debugLine="astreams1.Close";
_astreams1.Close();
 //BA.debugLineNum = 267;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 256;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 257;BA.debugLine="flag=1";
_flag = (int) (1);
 //BA.debugLineNum = 258;BA.debugLine="If flag_inicio_usb=True Then";
if (_flag_inicio_usb==anywheresoftware.b4a.keywords.Common.True) { 
 }else {
 //BA.debugLineNum = 260;BA.debugLine="openusb";
_openusb();
 };
 //BA.debugLineNum = 262;BA.debugLine="End Sub";
return "";
}
public static String  _astreams1_error() throws Exception{
 //BA.debugLineNum = 3541;BA.debugLine="Sub AStreams1_Error";
 //BA.debugLineNum = 3542;BA.debugLine="Log(\"Error: \" & LastException)";
anywheresoftware.b4a.keywords.Common.Log("Error: "+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 //BA.debugLineNum = 3544;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 3545;BA.debugLine="ToastMessageShow(\"ERROR EN LA LECTURA\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("ERROR EN LA LECTURA",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3546;BA.debugLine="astreams1.Close";
_astreams1.Close();
 //BA.debugLineNum = 3547;BA.debugLine="End Sub";
return "";
}
public static String  _astreams1_newdata(byte[] _buffer) throws Exception{
String _msg = "";
byte[] _b = null;
int[] _c = null;
String[] _nombre_leer = null;
int _result = 0;
int _i = 0;
 //BA.debugLineNum = 3402;BA.debugLine="Sub Astreams1_NewData (buffer() As Byte)";
 //BA.debugLineNum = 3403;BA.debugLine="Dim msg As String=\" \"";
_msg = " ";
 //BA.debugLineNum = 3404;BA.debugLine="Dim b(255) As Byte";
_b = new byte[(int) (255)];
;
 //BA.debugLineNum = 3405;BA.debugLine="Dim c(50) As Int";
_c = new int[(int) (50)];
;
 //BA.debugLineNum = 3406;BA.debugLine="Dim nombre_leer(50) As String";
_nombre_leer = new String[(int) (50)];
java.util.Arrays.fill(_nombre_leer,"");
 //BA.debugLineNum = 3407;BA.debugLine="Log(\"estamos en buffer 0 \"&buffer(0))";
anywheresoftware.b4a.keywords.Common.Log("estamos en buffer 0 "+BA.NumberToString(_buffer[(int) (0)]));
 //BA.debugLineNum = 3410;BA.debugLine="If buffer(0)=76 Then";
if (_buffer[(int) (0)]==76) { 
 //BA.debugLineNum = 3412;BA.debugLine="If flag_rx=False Then";
if (_flag_rx==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 3413;BA.debugLine="Log(\"LECTURA DE BYTES\")";
anywheresoftware.b4a.keywords.Common.Log("LECTURA DE BYTES");
 //BA.debugLineNum = 3414;BA.debugLine="flag_rx=True";
_flag_rx = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 3415;BA.debugLine="contador=0";
_contador = (int) (0);
 };
 };
 //BA.debugLineNum = 3420;BA.debugLine="If flag_rx=True Then";
if (_flag_rx==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 3422;BA.debugLine="contador=contador+1";
_contador = (int) (_contador+1);
 //BA.debugLineNum = 3424;BA.debugLine="If contador>224 Then";
if (_contador>224) { 
 //BA.debugLineNum = 3426;BA.debugLine="b(contador-225)=buffer(0)";
_b[(int) (_contador-225)] = _buffer[(int) (0)];
 //BA.debugLineNum = 3427;BA.debugLine="c(contador-225)=buffer(0)";
_c[(int) (_contador-225)] = (int) (_buffer[(int) (0)]);
 //BA.debugLineNum = 3429;BA.debugLine="If buffer(0)<> 0 Then";
if (_buffer[(int) (0)]!=0) { 
 //BA.debugLineNum = 3430;BA.debugLine="nombre_leer(contador-225)=Chr(c(contador-225))";
_nombre_leer[(int) (_contador-225)] = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(_c[(int) (_contador-225)]));
 //BA.debugLineNum = 3431;BA.debugLine="nombre_leido=nombre_leido&Chr(c(contador-225))";
mostCurrent._nombre_leido = mostCurrent._nombre_leido+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(_c[(int) (_contador-225)]));
 //BA.debugLineNum = 3432;BA.debugLine="Log(nombre_leido)";
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._nombre_leido);
 };
 };
 //BA.debugLineNum = 3438;BA.debugLine="If contador=255 Then";
if (_contador==255) { 
 //BA.debugLineNum = 3439;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 3441;BA.debugLine="Log(\"msg\"&	 BytesToString(b,0,b.Length,\"UTF8\"))";
anywheresoftware.b4a.keywords.Common.Log("msg"+anywheresoftware.b4a.keywords.Common.BytesToString(_b,(int) (0),_b.length,"UTF8"));
 //BA.debugLineNum = 3442;BA.debugLine="Log(\"FIN RX\")";
anywheresoftware.b4a.keywords.Common.Log("FIN RX");
 //BA.debugLineNum = 3445;BA.debugLine="flag_rx=False";
_flag_rx = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 3446;BA.debugLine="If File.Exists(File.DirRootExternal & \"/Consola";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_leido)) { 
 //BA.debugLineNum = 3447;BA.debugLine="Dim result As Int";
_result = 0;
 //BA.debugLineNum = 3448;BA.debugLine="result=Msgbox2(\"Desea abrir el fichero \"&nombr";
_result = anywheresoftware.b4a.keywords.Common.Msgbox2("Desea abrir el fichero "+mostCurrent._nombre_leido+"?","Abrir Fichero","Si","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 3449;BA.debugLine="If result=DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 3450;BA.debugLine="ToastMessageShow(\"Abrimos\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Abrimos",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3451;BA.debugLine="Log(lbModelo.Text)";
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._lbmodelo.getText());
 //BA.debugLineNum = 3452;BA.debugLine="If nombre_leido.Contains(lbModelo.Text) Then";
if (mostCurrent._nombre_leido.contains(mostCurrent._lbmodelo.getText())) { 
 //BA.debugLineNum = 3453;BA.debugLine="ToastMessageShow(\"estamos en el\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("estamos en el",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 3456;BA.debugLine="Abrir_Fichero_leido(nombre_leido)";
_abrir_fichero_leido(mostCurrent._nombre_leido);
 };
 };
 //BA.debugLineNum = 3460;BA.debugLine="If result=DialogResponse.NEGATIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
 //BA.debugLineNum = 3461;BA.debugLine="ToastMessageShow(\"Cerramos\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Cerramos",anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 3464;BA.debugLine="Msgbox(\"No exite el fichero\",\"Error\")";
anywheresoftware.b4a.keywords.Common.Msgbox("No exite el fichero","Error",mostCurrent.activityBA);
 };
 };
 };
 //BA.debugLineNum = 3473;BA.debugLine="If flag_rx=False Then";
if (_flag_rx==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 3474;BA.debugLine="If buffer(0)=69 Then";
if (_buffer[(int) (0)]==69) { 
 //BA.debugLineNum = 3475;BA.debugLine="Leer_fichero";
_leer_fichero();
 //BA.debugLineNum = 3476;BA.debugLine="Log(\"GRABACION DE BYTES\")";
anywheresoftware.b4a.keywords.Common.Log("GRABACION DE BYTES");
 //BA.debugLineNum = 3478;BA.debugLine="astreams1.Write2(buffer_tx,0,191)";
_astreams1.Write2(_buffer_tx,(int) (0),(int) (191));
 //BA.debugLineNum = 3480;BA.debugLine="astreams1.Write2(buffer_tx,192,64)";
_astreams1.Write2(_buffer_tx,(int) (192),(int) (64));
 //BA.debugLineNum = 3481;BA.debugLine="For i=0 To buffer_tx.Length-1";
{
final int step2244 = 1;
final int limit2244 = (int) (_buffer_tx.length-1);
for (_i = (int) (0); (step2244 > 0 && _i <= limit2244) || (step2244 < 0 && _i >= limit2244); _i = ((int)(0 + _i + step2244))) {
 //BA.debugLineNum = 3482;BA.debugLine="Log(\"buffer grab: \"&buffer_tx(i))";
anywheresoftware.b4a.keywords.Common.Log("buffer grab: "+BA.NumberToString(_buffer_tx[_i]));
 }
};
 };
 };
 //BA.debugLineNum = 3486;BA.debugLine="If flag_rx=False Then";
if (_flag_rx==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 3487;BA.debugLine="If buffer(0)=77 Then";
if (_buffer[(int) (0)]==77) { 
 //BA.debugLineNum = 3488;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 3489;BA.debugLine="Msgbox(\"Modelo no corresponde con modalidad\",\"E";
anywheresoftware.b4a.keywords.Common.Msgbox("Modelo no corresponde con modalidad","Error",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 3491;BA.debugLine="If buffer(0)=70 Then";
if (_buffer[(int) (0)]==70) { 
 //BA.debugLineNum = 3492;BA.debugLine="Log(\"FIN GRABACION\")";
anywheresoftware.b4a.keywords.Common.Log("FIN GRABACION");
 //BA.debugLineNum = 3493;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 };
 };
 //BA.debugLineNum = 3497;BA.debugLine="End Sub";
return "";
}
public static String  _astreams1_terminated() throws Exception{
 //BA.debugLineNum = 3549;BA.debugLine="Sub Astreams1_Terminated";
 //BA.debugLineNum = 3550;BA.debugLine="Log(\"Terminated\")";
anywheresoftware.b4a.keywords.Common.Log("Terminated");
 //BA.debugLineNum = 3551;BA.debugLine="astreams1.Close";
_astreams1.Close();
 //BA.debugLineNum = 3552;BA.debugLine="End Sub";
return "";
}
public static String  _borrar() throws Exception{
anywheresoftware.b4a.objects.collections.List _filestodelete = null;
int _i = 0;
 //BA.debugLineNum = 3289;BA.debugLine="Sub borrar";
 //BA.debugLineNum = 3290;BA.debugLine="Dim FilesToDelete As List";
_filestodelete = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 3292;BA.debugLine="FilesToDelete.Initialize";
_filestodelete.Initialize();
 //BA.debugLineNum = 3293;BA.debugLine="FilesToDelete.AddAll(File.ListFiles(File.DirRootEx";
_filestodelete.AddAll(anywheresoftware.b4a.keywords.Common.File.ListFiles(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A"));
 //BA.debugLineNum = 3295;BA.debugLine="For I = 0 To FilesToDelete.Size -1";
{
final int step2133 = 1;
final int limit2133 = (int) (_filestodelete.getSize()-1);
for (_i = (int) (0); (step2133 > 0 && _i <= limit2133) || (step2133 < 0 && _i >= limit2133); _i = ((int)(0 + _i + step2133))) {
 //BA.debugLineNum = 3297;BA.debugLine="File.Delete(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A",BA.ObjectToString(_filestodelete.Get(_i)));
 }
};
 //BA.debugLineNum = 3301;BA.debugLine="End Sub";
return "";
}
public static String  _borrar_datos(int _valor_ini,int _valor_fin,int _valor_sum) throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
 //BA.debugLineNum = 3238;BA.debugLine="Sub borrar_datos (valor_ini As Int, valor_fin As I";
 //BA.debugLineNum = 3239;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 3240;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 3241;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 3242;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 3243;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 3247;BA.debugLine="If posicion_mod<valor_fin Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<_valor_fin) { 
 //BA.debugLineNum = 3248;BA.debugLine="If posicion_mod>=valor_ini Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=_valor_ini) { 
 //BA.debugLineNum = 3249;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 3250;BA.debugLine="pos_valor=(pos_mod_aux*4)+valor_sum";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+_valor_sum);
 //BA.debugLineNum = 3253;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 3254;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 3255;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 3256;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 };
 };
 //BA.debugLineNum = 3268;BA.debugLine="End Sub";
return "";
}
public static String  _btcomenzar_longclick() throws Exception{
 //BA.debugLineNum = 3312;BA.debugLine="Sub btComenzar_LongClick";
 //BA.debugLineNum = 3313;BA.debugLine="flag=2";
_flag = (int) (2);
 //BA.debugLineNum = 3314;BA.debugLine="Log(\"flag F1A_: \"&flag)";
anywheresoftware.b4a.keywords.Common.Log("flag F1A_: "+BA.NumberToString(_flag));
 //BA.debugLineNum = 3316;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 3317;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 3318;BA.debugLine="End Sub";
return "";
}
public static String  _btgrabar_longclick() throws Exception{
 //BA.debugLineNum = 3345;BA.debugLine="Sub btgrabar_LongClick";
 //BA.debugLineNum = 3365;BA.debugLine="If astreams1.IsInitialized Then";
if (_astreams1.IsInitialized()) { 
 }else {
 //BA.debugLineNum = 3368;BA.debugLine="astreams1.Initialize(usb1.GetInputStream, usb1";
_astreams1.Initialize(processBA,_usb1.GetInputStream(),_usb1.GetOutputStream(),"astreams1");
 };
 //BA.debugLineNum = 3371;BA.debugLine="flag_d=False";
_flag_d = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 3373;BA.debugLine="flag_lectura=False";
_flag_lectura = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 3376;BA.debugLine="flag_rx=False";
_flag_rx = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 3377;BA.debugLine="send_data(\"E\")";
_send_data("E");
 //BA.debugLineNum = 3378;BA.debugLine="ProgressDialogShow(\"Grabando fichero...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Grabando fichero...");
 //BA.debugLineNum = 3386;BA.debugLine="End Sub";
return "";
}
public static String  _btleer_click() throws Exception{
 //BA.debugLineNum = 3616;BA.debugLine="Sub btleer_Click";
 //BA.debugLineNum = 3634;BA.debugLine="If astreams1.IsInitialized Then";
if (_astreams1.IsInitialized()) { 
 }else {
 //BA.debugLineNum = 3637;BA.debugLine="astreams1.Initialize(usb1.GetInputStream, usb1";
_astreams1.Initialize(processBA,_usb1.GetInputStream(),_usb1.GetOutputStream(),"astreams1");
 };
 //BA.debugLineNum = 3645;BA.debugLine="flag_lectura=True";
_flag_lectura = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 3646;BA.debugLine="flag_rx=False";
_flag_rx = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 3647;BA.debugLine="send_data(\"L\")";
_send_data("L");
 //BA.debugLineNum = 3648;BA.debugLine="ProgressDialogShow(\"Leyendo informacion, Por fa";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Leyendo informacion, Por favor espere....");
 //BA.debugLineNum = 3656;BA.debugLine="nombre_leido=\"\"";
mostCurrent._nombre_leido = "";
 //BA.debugLineNum = 3657;BA.debugLine="End Sub";
return "";
}
public static String  _btnclose_click() throws Exception{
 //BA.debugLineNum = 3533;BA.debugLine="Sub btnClose_Click";
 //BA.debugLineNum = 3534;BA.debugLine="astreams1.Close";
_astreams1.Close();
 //BA.debugLineNum = 3535;BA.debugLine="usb1.Close";
_usb1.Close();
 //BA.debugLineNum = 3539;BA.debugLine="End Sub";
return "";
}
public static String  _btok_click() throws Exception{
String _nom_ser = "";
String _aux_valor_giro = "";
int _i = 0;
 //BA.debugLineNum = 2362;BA.debugLine="Sub btOK_Click";
 //BA.debugLineNum = 2363;BA.debugLine="Try";
try { //BA.debugLineNum = 2364;BA.debugLine="Dim nom_ser As String";
_nom_ser = "";
 //BA.debugLineNum = 2366;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 2368;BA.debugLine="If contador_lineas<=5 Then";
if (_contador_lineas<=5) { 
 //BA.debugLineNum = 2369;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2370;BA.debugLine="nom_ser=\"STABILO\"";
_nom_ser = "STABILO";
 };
 //BA.debugLineNum = 2372;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 2373;BA.debugLine="nom_ser=\"DIRECCION  \"";
_nom_ser = "DIRECCION  ";
 };
 //BA.debugLineNum = 2375;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 2376;BA.debugLine="nom_ser=\"C.INCI.ALA\"";
_nom_ser = "C.INCI.ALA";
 };
 //BA.debugLineNum = 2378;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 2379;BA.debugLine="nom_ser=\"FLAPPER\"";
_nom_ser = "FLAPPER";
 };
 //BA.debugLineNum = 2381;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 2382;BA.debugLine="nom_ser=\"GANCHO/FLAP\"";
_nom_ser = "GANCHO/FLAP";
 };
 //BA.debugLineNum = 2387;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 2389;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2390;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2392;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 2393;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2395;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 2396;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2398;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 2399;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 2401;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 2402;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 //BA.debugLineNum = 2405;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2406;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 2407;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2411;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2412;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2413;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2414;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2419;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2421;BA.debugLine="lista1.Add(giro)";
mostCurrent._lista1.Add((Object)(mostCurrent._giro));
 };
 //BA.debugLineNum = 2424;BA.debugLine="If contador_lineas=5 Then";
if (_contador_lineas==5) { 
 //BA.debugLineNum = 2425;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2426;BA.debugLine="Label9.Visible=True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2427;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 2428;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 //BA.debugLineNum = 2429;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 }else {
 //BA.debugLineNum = 2431;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2432;BA.debugLine="lbNumeroServo.Text=contador_lineas";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas));
 //BA.debugLineNum = 2433;BA.debugLine="If contador_lineas=1 Then";
if (_contador_lineas==1) { 
 //BA.debugLineNum = 2434;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2436;BA.debugLine="If contador_lineas=2 Then";
if (_contador_lineas==2) { 
 //BA.debugLineNum = 2437;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2439;BA.debugLine="If contador_lineas=3 Then";
if (_contador_lineas==3) { 
 //BA.debugLineNum = 2440;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2442;BA.debugLine="If contador_lineas=4 Then";
if (_contador_lineas==4) { 
 //BA.debugLineNum = 2443;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 2445;BA.debugLine="If contador_lineas=5 Then";
if (_contador_lineas==5) { 
 //BA.debugLineNum = 2446;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 };
 };
 //BA.debugLineNum = 2456;BA.debugLine="If contador_lineas>=6 Then";
if (_contador_lineas>=6) { 
 //BA.debugLineNum = 2458;BA.debugLine="If contador_lineas<=11 Then";
if (_contador_lineas<=11) { 
 //BA.debugLineNum = 2459;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 2461;BA.debugLine="If contador_lineas>=7 Then";
if (_contador_lineas>=7) { 
 //BA.debugLineNum = 2462;BA.debugLine="Label9.Text=\"RDT\"";
mostCurrent._label9.setText((Object)("RDT"));
 //BA.debugLineNum = 2463;BA.debugLine="lbNumeroServo.Enabled=True";
mostCurrent._lbnumeroservo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2464;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 2465;BA.debugLine="If contador_lineas-5=1 Then";
if (_contador_lineas-5==1) { 
 //BA.debugLineNum = 2466;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2468;BA.debugLine="If contador_lineas-5=2 Then";
if (_contador_lineas-5==2) { 
 //BA.debugLineNum = 2469;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2471;BA.debugLine="If contador_lineas-5=3 Then";
if (_contador_lineas-5==3) { 
 //BA.debugLineNum = 2472;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2474;BA.debugLine="If contador_lineas-5=4 Then";
if (_contador_lineas-5==4) { 
 //BA.debugLineNum = 2475;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 2477;BA.debugLine="If contador_lineas-5=5 Then";
if (_contador_lineas-5==5) { 
 //BA.debugLineNum = 2478;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 //BA.debugLineNum = 2480;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2481;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 2482;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2486;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2487;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2488;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2489;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2494;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2496;BA.debugLine="lista1.Add(giro)";
mostCurrent._lista1.Add((Object)(mostCurrent._giro));
 };
 //BA.debugLineNum = 2501;BA.debugLine="If contador_lineas=11 Then";
if (_contador_lineas==11) { 
 //BA.debugLineNum = 2502;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2503;BA.debugLine="Label9.Text=\"R.Circular\"";
mostCurrent._label9.setText((Object)("R.Circular"));
 //BA.debugLineNum = 2504;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 2505;BA.debugLine="lbservotxt.Text=\"Stabilo\"";
mostCurrent._lbservotxt.setText((Object)("Stabilo"));
 //BA.debugLineNum = 2506;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 }else {
 //BA.debugLineNum = 2525;BA.debugLine="lbNumeroServo.Text=contador_lineas";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas));
 };
 };
 //BA.debugLineNum = 2545;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2546;BA.debugLine="lbNumeroServo.Text=contador_lineas-6";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas-6));
 };
 };
 //BA.debugLineNum = 2552;BA.debugLine="If contador_lineas>=13 Then";
if (_contador_lineas>=13) { 
 //BA.debugLineNum = 2553;BA.debugLine="If contador_lineas<19 Then";
if (_contador_lineas<19) { 
 //BA.debugLineNum = 2554;BA.debugLine="If contador_lineas>=14 Then";
if (_contador_lineas>=14) { 
 //BA.debugLineNum = 2555;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 2556;BA.debugLine="If contador_lineas-12=1 Then";
if (_contador_lineas-12==1) { 
 //BA.debugLineNum = 2557;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2559;BA.debugLine="If contador_lineas-12=2 Then";
if (_contador_lineas-12==2) { 
 //BA.debugLineNum = 2560;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2562;BA.debugLine="If contador_lineas-12=3 Then";
if (_contador_lineas-12==3) { 
 //BA.debugLineNum = 2563;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2565;BA.debugLine="If contador_lineas-12=4 Then";
if (_contador_lineas-12==4) { 
 //BA.debugLineNum = 2566;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 2568;BA.debugLine="If contador_lineas-12=5 Then";
if (_contador_lineas-12==5) { 
 //BA.debugLineNum = 2569;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 //BA.debugLineNum = 2571;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2572;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 2573;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2577;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2578;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2579;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2580;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2585;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2587;BA.debugLine="lista1.Add(giro)";
mostCurrent._lista1.Add((Object)(mostCurrent._giro));
 };
 //BA.debugLineNum = 2591;BA.debugLine="If contador_lineas=18 Then";
if (_contador_lineas==18) { 
 //BA.debugLineNum = 2592;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2593;BA.debugLine="Label9.Text=\"Pre-Start\"";
mostCurrent._label9.setText((Object)("Pre-Start"));
 //BA.debugLineNum = 2594;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 2595;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2596;BA.debugLine="lbNumeroServo.Text=contador_lineas-6";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas-6));
 //BA.debugLineNum = 2597;BA.debugLine="If contador_lineas-6=1 Then";
if (_contador_lineas-6==1) { 
 //BA.debugLineNum = 2598;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2600;BA.debugLine="If contador_lineas-6=2 Then";
if (_contador_lineas-6==2) { 
 //BA.debugLineNum = 2601;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2603;BA.debugLine="If contador_lineas-6=3 Then";
if (_contador_lineas-6==3) { 
 //BA.debugLineNum = 2604;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2606;BA.debugLine="If contador_lineas-6=4 Then";
if (_contador_lineas-6==4) { 
 //BA.debugLineNum = 2607;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 2609;BA.debugLine="If contador_lineas-6=5 Then";
if (_contador_lineas-6==5) { 
 //BA.debugLineNum = 2610;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 }else {
 //BA.debugLineNum = 2614;BA.debugLine="lbNumeroServo.Text=contador_lineas";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas));
 };
 //BA.debugLineNum = 2616;BA.debugLine="lbNumeroServo.Text=contador_lineas";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas));
 };
 //BA.debugLineNum = 2618;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2619;BA.debugLine="lbNumeroServo.Text=contador_lineas-13";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas-13));
 };
 };
 //BA.debugLineNum = 2632;BA.debugLine="If contador_lineas>=20 Then";
if (_contador_lineas>=20) { 
 //BA.debugLineNum = 2633;BA.debugLine="If contador_lineas<=26 Then";
if (_contador_lineas<=26) { 
 //BA.debugLineNum = 2634;BA.debugLine="If contador_lineas>=21 Then";
if (_contador_lineas>=21) { 
 //BA.debugLineNum = 2635;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 2636;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2637;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 2638;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2642;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2643;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2644;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2645;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2650;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2652;BA.debugLine="lista1.Add(giro)";
mostCurrent._lista1.Add((Object)(mostCurrent._giro));
 };
 };
 //BA.debugLineNum = 2657;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2658;BA.debugLine="lbNumeroServo.Text=contador_lineas-20";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas-20));
 //BA.debugLineNum = 2659;BA.debugLine="If contador_lineas-20=1 Then";
if (_contador_lineas-20==1) { 
 //BA.debugLineNum = 2660;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2662;BA.debugLine="If contador_lineas-20=2 Then";
if (_contador_lineas-20==2) { 
 //BA.debugLineNum = 2663;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2665;BA.debugLine="If contador_lineas-20=3 Then";
if (_contador_lineas-20==3) { 
 //BA.debugLineNum = 2666;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2668;BA.debugLine="If contador_lineas-20=4 Then";
if (_contador_lineas-20==4) { 
 //BA.debugLineNum = 2669;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 2671;BA.debugLine="If contador_lineas-20=5 Then";
if (_contador_lineas-20==5) { 
 //BA.debugLineNum = 2672;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 };
 };
 //BA.debugLineNum = 2684;BA.debugLine="If contador_lineas=26 Then";
if (_contador_lineas==26) { 
 //BA.debugLineNum = 2685;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2686;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2687;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2688;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2689;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2690;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2691;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2692;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2693;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 2694;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step1735 = 1;
final int limit1735 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0); (step1735 > 0 && _i <= limit1735) || (step1735 < 0 && _i >= limit1735); _i = ((int)(0 + _i + step1735))) {
 //BA.debugLineNum = 2696;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 2701;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2702;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2703;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2704;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2705;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2706;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2707;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2708;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2709;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2710;BA.debugLine="datos_lv";
_datos_lv();
 //BA.debugLineNum = 2711;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 }else {
 };
 //BA.debugLineNum = 2721;BA.debugLine="Log(\"numero lineas  \"&contador_lineas)";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString(_contador_lineas));
 //BA.debugLineNum = 2723;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola/F1";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 2724;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 } 
       catch (Exception e1755) {
			processBA.setLastException(e1755); };
 //BA.debugLineNum = 2731;BA.debugLine="End Sub";
return "";
}
public static String  _btsalir_longclick() throws Exception{
 //BA.debugLineNum = 3303;BA.debugLine="Sub btSalir_LongClick";
 //BA.debugLineNum = 3305;BA.debugLine="flag=1";
_flag = (int) (1);
 //BA.debugLineNum = 3306;BA.debugLine="Log(\"flag F1A: \"&flag)";
anywheresoftware.b4a.keywords.Common.Log("flag F1A: "+BA.NumberToString(_flag));
 //BA.debugLineNum = 3309;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 3310;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 2225;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 2226;BA.debugLine="etGiro.ForceDoneButton=False";
mostCurrent._etgiro.setForceDoneButton(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2227;BA.debugLine="etTiempo_1";
_ettiempo_1();
 //BA.debugLineNum = 2229;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
int _i = 0;
 //BA.debugLineNum = 1140;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 1142;BA.debugLine="If spServo.SelectedItem=\"PULSE AQUI\" Then";
if ((mostCurrent._spservo.getSelectedItem()).equals("PULSE AQUI")) { 
 };
 //BA.debugLineNum = 1145;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 1146;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 1147;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 1148;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 1149;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 1151;BA.debugLine="If posicion_mod<20 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<20) { 
 //BA.debugLineNum = 1156;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 1159;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 1160;BA.debugLine="lista1.set(posicion_mod,aux_valor_giro)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 1163;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 1164;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 1166;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 1167;BA.debugLine="lista1.set(posicion_mod,aux_valor_giro)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 1171;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 1172;BA.debugLine="aux_valor_giro=giro";
_aux_valor_giro = mostCurrent._giro;
 //BA.debugLineNum = 1173;BA.debugLine="lista1.set(posicion_mod,aux_valor_giro)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 1176;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1177;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1178;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1179;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1180;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1181;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1182;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1183;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1184;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1185;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1191;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 1192;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 1221;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1222;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (0)))+"  REMOLQUE");
 //BA.debugLineNum = 1223;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1224;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (1)))+"  REMOLQUE");
 //BA.debugLineNum = 1225;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1226;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (2)))+"  REMOLQUE");
 //BA.debugLineNum = 1227;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1228;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (3)))+"  REMOLQUE");
 //BA.debugLineNum = 1229;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1230;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (4)))+"  REMOLQUE");
 //BA.debugLineNum = 1232;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1233;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (5)))+"  RDT");
 //BA.debugLineNum = 1234;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1235;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (6)))+"  RDT");
 //BA.debugLineNum = 1236;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1237;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (7)))+"  RDT");
 //BA.debugLineNum = 1238;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1239;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (8)))+"  RDT");
 //BA.debugLineNum = 1240;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1241;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (9)))+"  RDT");
 //BA.debugLineNum = 1243;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1244;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (10)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1245;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1246;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (11)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1247;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1248;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (12)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1249;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1250;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (13)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1251;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1252;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (14)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1254;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1255;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (15)))+"  PRE-START");
 //BA.debugLineNum = 1256;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1257;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (16)))+"  PRE-START");
 //BA.debugLineNum = 1258;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1259;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (17)))+"  PRE-START");
 //BA.debugLineNum = 1260;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1261;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (18)))+"  PRE-START");
 //BA.debugLineNum = 1262;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1263;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (19)))+"  PRE-START");
 //BA.debugLineNum = 1265;BA.debugLine="For i=20 To lista1.Size-1 Step 4";
{
final int step877 = (int) (4);
final int limit877 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (20); (step877 > 0 && _i <= limit877) || (step877 < 0 && _i >= limit877); _i = ((int)(0 + _i + step877))) {
 //BA.debugLineNum = 1266;BA.debugLine="tiempo_aux=lista1.Get(i)";
mostCurrent._tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get(_i));
 //BA.debugLineNum = 1267;BA.debugLine="tipo_tiempo_aux=lista1.Get(i+1)";
mostCurrent._tipo_tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+1)));
 //BA.debugLineNum = 1268;BA.debugLine="servo_aux=lista1.Get(i+2)";
mostCurrent._servo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+2)));
 //BA.debugLineNum = 1269;BA.debugLine="giro_aux=lista1.Get(i+3)";
mostCurrent._giro_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+3)));
 //BA.debugLineNum = 1271;BA.debugLine="If servo_aux=1 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1272;BA.debugLine="servo_aux_nombre=\"INC.STABILO 1\"";
mostCurrent._servo_aux_nombre = "INC.STABILO 1";
 };
 //BA.debugLineNum = 1274;BA.debugLine="If servo_aux=2 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1275;BA.debugLine="servo_aux_nombre=\"DIRECCION   2\"";
mostCurrent._servo_aux_nombre = "DIRECCION   2";
 };
 //BA.debugLineNum = 1277;BA.debugLine="If servo_aux=3 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 1278;BA.debugLine="servo_aux_nombre=\"C.INCI.ALAS 3\"";
mostCurrent._servo_aux_nombre = "C.INCI.ALAS 3";
 };
 //BA.debugLineNum = 1280;BA.debugLine="If servo_aux=4 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 1281;BA.debugLine="servo_aux_nombre=\"FLAPPER     4\"";
mostCurrent._servo_aux_nombre = "FLAPPER     4";
 };
 //BA.debugLineNum = 1283;BA.debugLine="If servo_aux=5 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 1284;BA.debugLine="servo_aux_nombre=\"GANCHO/FLAP 5\"";
mostCurrent._servo_aux_nombre = "GANCHO/FLAP 5";
 };
 //BA.debugLineNum = 1289;BA.debugLine="If tipo_tiempo_aux=\"0\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("0")) { 
 //BA.debugLineNum = 1290;BA.debugLine="tipo_tiempo=\"DECIMAS\"";
mostCurrent._tipo_tiempo = "DECIMAS";
 //BA.debugLineNum = 1291;BA.debugLine="tiempo_aux=tiempo_aux";
mostCurrent._tiempo_aux = mostCurrent._tiempo_aux;
 };
 //BA.debugLineNum = 1294;BA.debugLine="If tipo_tiempo_aux=1 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1295;BA.debugLine="tipo_tiempo=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo = "SEGUNDOS";
 };
 //BA.debugLineNum = 1299;BA.debugLine="If tipo_tiempo_aux=2 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1300;BA.debugLine="tipo_tiempo=\"MINUTOS\"";
mostCurrent._tipo_tiempo = "MINUTOS";
 };
 //BA.debugLineNum = 1303;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1305;BA.debugLine="lvDatos.AddSingleLine(servo_aux_nombre&\"   Gir";
mostCurrent._lvdatos.AddSingleLine(mostCurrent._servo_aux_nombre+"   Giro: "+mostCurrent._giro_aux+"    Tiempo: "+mostCurrent._tiempo_aux+"  Tipo tiempo: "+mostCurrent._tipo_tiempo);
 }
};
 //BA.debugLineNum = 1309;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1310;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1311;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1312;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1315;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1316;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1317;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1318;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1319;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1320;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1321;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1322;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 1323;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1324;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1325;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1326;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1329;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1330;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1331;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1336;BA.debugLine="etTiempo_2";
_ettiempo_2();
 //BA.debugLineNum = 1339;BA.debugLine="End Sub";
return "";
}
public static String  _cbled_checkedchange(boolean _checked) throws Exception{
int _i = 0;
 //BA.debugLineNum = 3597;BA.debugLine="Sub cbled_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 3598;BA.debugLine="If Checked=True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 3599;BA.debugLine="cbled.Text=\"Led ON\"";
mostCurrent._cbled.setText((Object)("Led ON"));
 //BA.debugLineNum = 3601;BA.debugLine="listaconfiguracion.Set(0,\"1\")";
mostCurrent._listaconfiguracion.Set((int) (0),(Object)("1"));
 }else {
 //BA.debugLineNum = 3604;BA.debugLine="cbled.Text=\"Led OFF\"";
mostCurrent._cbled.setText((Object)("Led OFF"));
 //BA.debugLineNum = 3605;BA.debugLine="listaconfiguracion.Set(0,\"0\")";
mostCurrent._listaconfiguracion.Set((int) (0),(Object)("0"));
 };
 //BA.debugLineNum = 3608;BA.debugLine="For i=0 To listaconfiguracion.Size-1";
{
final int step2314 = 1;
final int limit2314 = (int) (mostCurrent._listaconfiguracion.getSize()-1);
for (_i = (int) (0); (step2314 > 0 && _i <= limit2314) || (step2314 < 0 && _i >= limit2314); _i = ((int)(0 + _i + step2314))) {
 //BA.debugLineNum = 3609;BA.debugLine="Log(listaconfiguracion.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._listaconfiguracion.Get(_i)));
 }
};
 //BA.debugLineNum = 3611;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consol";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A/configuracion","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._listaconfiguracion);
 //BA.debugLineNum = 3613;BA.debugLine="End Sub";
return "";
}
public static String  _datos_lv() throws Exception{
 //BA.debugLineNum = 2760;BA.debugLine="Sub datos_lv";
 //BA.debugLineNum = 2788;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2789;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (0)))+"  REMOLQUE");
 //BA.debugLineNum = 2790;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2791;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (1)))+"  REMOLQUE");
 //BA.debugLineNum = 2792;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2793;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (2)))+"  REMOLQUE");
 //BA.debugLineNum = 2794;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2795;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (3)))+"  REMOLQUE");
 //BA.debugLineNum = 2796;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2797;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (4)))+"  REMOLQUE");
 //BA.debugLineNum = 2799;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2800;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (5)))+"  RDT");
 //BA.debugLineNum = 2801;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2802;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (6)))+"  RDT");
 //BA.debugLineNum = 2803;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2804;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (7)))+"  RDT");
 //BA.debugLineNum = 2805;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2806;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (8)))+"  RDT");
 //BA.debugLineNum = 2807;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2808;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (9)))+"  RDT");
 //BA.debugLineNum = 2810;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2811;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (10)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2812;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2813;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (11)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2814;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2815;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (12)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2816;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2817;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (13)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2818;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2819;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (14)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 2821;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2822;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (15)))+"  PRE-START");
 //BA.debugLineNum = 2823;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2824;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (16)))+"  PRE-START");
 //BA.debugLineNum = 2825;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2826;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (17)))+"  PRE-START");
 //BA.debugLineNum = 2827;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2828;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (18)))+"  PRE-START");
 //BA.debugLineNum = 2829;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2830;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (19)))+"  PRE-START");
 //BA.debugLineNum = 2833;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 2834;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 2836;BA.debugLine="End Sub";
return "";
}
public static String  _elegir_modelo() throws Exception{
int _valor = 0;
 //BA.debugLineNum = 269;BA.debugLine="Sub elegir_modelo";
 //BA.debugLineNum = 270;BA.debugLine="Dim valor As Int";
_valor = 0;
 //BA.debugLineNum = 271;BA.debugLine="dial.Input=\"\"";
mostCurrent._dial.setInput("");
 //BA.debugLineNum = 272;BA.debugLine="valor=	dial.Show(\"\",\"NOMBRE MODELO\",\"Aceptar\",\"\",\"";
_valor = mostCurrent._dial.Show("","NOMBRE MODELO","Aceptar","","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 275;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 276;BA.debugLine="If dial.Response=-1 Then";
if (mostCurrent._dial.getResponse()==-1) { 
 };
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return "";
}
public static String  _enviar_configuracion() throws Exception{
 //BA.debugLineNum = 3499;BA.debugLine="Sub enviar_configuracion";
 //BA.debugLineNum = 3519;BA.debugLine="End Sub";
return "";
}
public static String  _etgiro_textchanged(String _old,String _new) throws Exception{
int _aux = 0;
String _aux1 = "";
 //BA.debugLineNum = 1962;BA.debugLine="Sub etGiro_TextChanged (Old As String, New As Stri";
 //BA.debugLineNum = 1963;BA.debugLine="Try";
try { //BA.debugLineNum = 1964;BA.debugLine="Dim aux As Int";
_aux = 0;
 //BA.debugLineNum = 1965;BA.debugLine="Dim aux1 As String";
_aux1 = "";
 //BA.debugLineNum = 1972;BA.debugLine="If New=\"\" Then";
if ((_new).equals("")) { 
 //BA.debugLineNum = 1974;BA.debugLine="giro=\"\"";
mostCurrent._giro = "";
 }else {
 //BA.debugLineNum = 1979;BA.debugLine="If New>0 Then";
if ((double)(Double.parseDouble(_new))>0) { 
 //BA.debugLineNum = 1980;BA.debugLine="If New<=200 Then";
if ((double)(Double.parseDouble(_new))<=200) { 
 //BA.debugLineNum = 1981;BA.debugLine="giro=New";
mostCurrent._giro = _new;
 //BA.debugLineNum = 1983;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1984;BA.debugLine="If flag_button2=True Then";
if (_flag_button2==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1985;BA.debugLine="Button2.Enabled=True";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1986;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1987;BA.debugLine="flag_button2=False";
_flag_button2 = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1989;BA.debugLine="Log(\"giro_text : \"&giro)";
anywheresoftware.b4a.keywords.Common.Log("giro_text : "+mostCurrent._giro);
 }else {
 //BA.debugLineNum = 1992;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 };
 }else {
 //BA.debugLineNum = 1995;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 };
 };
 } 
       catch (Exception e1365) {
			processBA.setLastException(e1365); };
 //BA.debugLineNum = 2001;BA.debugLine="End Sub";
return "";
}
public static String  _ettiempo_1() throws Exception{
boolean _ret = false;
String _str = "";
int _aux_tiempo = 0;
 //BA.debugLineNum = 1341;BA.debugLine="Sub etTiempo_1";
 //BA.debugLineNum = 1342;BA.debugLine="Try";
try { //BA.debugLineNum = 1343;BA.debugLine="Dim ret As Boolean";
_ret = false;
 //BA.debugLineNum = 1344;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 1345;BA.debugLine="Dim aux_tiempo As  Int";
_aux_tiempo = 0;
 //BA.debugLineNum = 1356;BA.debugLine="tiempo_actual=etTiempo.Text";
_tiempo_actual = (int)(Double.parseDouble(mostCurrent._ettiempo.getText()));
 //BA.debugLineNum = 1357;BA.debugLine="Log(\"Tiempo Actual sin convertir: \"&tiempo_actual)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual sin convertir: "+BA.NumberToString(_tiempo_actual));
 //BA.debugLineNum = 1358;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1359;BA.debugLine="tiempo_actual=tiempo_actual";
_tiempo_actual = _tiempo_actual;
 //BA.debugLineNum = 1360;BA.debugLine="Log(\"Tiempo Actual decimas convertir decimas: \"&ti";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual decimas convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1363;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1364;BA.debugLine="tiempo_actual=tiempo_actual*10";
_tiempo_actual = (int) (_tiempo_actual*10);
 //BA.debugLineNum = 1365;BA.debugLine="Log(\"Tiempo Actual segundos convertir decimas: \"&t";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1367;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1368;BA.debugLine="tiempo_actual=tiempo_actual*600";
_tiempo_actual = (int) (_tiempo_actual*600);
 //BA.debugLineNum = 1369;BA.debugLine="Log(\"Tiempo Actual minutos convertir decimas: \"&ti";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1372;BA.debugLine="Log(\"Tiempo minimo: \"&tiempo_minimo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo minimo: "+BA.NumberToString(_tiempo_minimo));
 //BA.debugLineNum = 1375;BA.debugLine="If etTiempo.Text=0 Then";
if ((mostCurrent._ettiempo.getText()).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1376;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 }else {
 //BA.debugLineNum = 1380;BA.debugLine="Log(\"iempo antes de grabar \"&tiempo_actual)";
anywheresoftware.b4a.keywords.Common.Log("iempo antes de grabar "+BA.NumberToString(_tiempo_actual));
 //BA.debugLineNum = 1381;BA.debugLine="If tiempo_actual=tiempo_minimo Then";
if (_tiempo_actual==_tiempo_minimo) { 
 //BA.debugLineNum = 1383;BA.debugLine="Log(\"tiempo =\")";
anywheresoftware.b4a.keywords.Common.Log("tiempo =");
 //BA.debugLineNum = 1384;BA.debugLine="ret=mirar_tiempo_servo(servo,tiempo_actual)";
_ret = _mirar_tiempo_servo((int)(Double.parseDouble(mostCurrent._servo)),_tiempo_actual);
 //BA.debugLineNum = 1385;BA.debugLine="If ret=True Then";
if (_ret==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1386;BA.debugLine="grabar";
_grabar();
 //BA.debugLineNum = 1387;BA.debugLine="ret=False";
_ret = anywheresoftware.b4a.keywords.Common.False;
 };
 };
 //BA.debugLineNum = 1393;BA.debugLine="If tiempo_actual>tiempo_minimo Then";
if (_tiempo_actual>_tiempo_minimo) { 
 //BA.debugLineNum = 1395;BA.debugLine="tiempo=etTiempo.Text";
mostCurrent._tiempo = mostCurrent._ettiempo.getText();
 //BA.debugLineNum = 1396;BA.debugLine="tiempo_minimo=tiempo_actual";
_tiempo_minimo = _tiempo_actual;
 //BA.debugLineNum = 1397;BA.debugLine="Log(\"INT de tiempo:  \" & tiempo_minimo)";
anywheresoftware.b4a.keywords.Common.Log("INT de tiempo:  "+BA.NumberToString(_tiempo_minimo));
 //BA.debugLineNum = 1398;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1399;BA.debugLine="Log(\"iempo antes de grabar \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("iempo antes de grabar "+mostCurrent._tiempo);
 //BA.debugLineNum = 1400;BA.debugLine="grabar";
_grabar();
 };
 //BA.debugLineNum = 1403;BA.debugLine="If tiempo_actual<tiempo_minimo Then";
if (_tiempo_actual<_tiempo_minimo) { 
 //BA.debugLineNum = 1405;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1406;BA.debugLine="Msgbox(\"Tiempo menor que el anterior\",\"ERROR\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Tiempo menor que el anterior","ERROR",mostCurrent.activityBA);
 };
 };
 } 
       catch (Exception e978) {
			processBA.setLastException(e978); };
 //BA.debugLineNum = 1419;BA.debugLine="End Sub";
return "";
}
public static String  _ettiempo_2() throws Exception{
boolean _ret = false;
String _str = "";
int _aux_tiempo = 0;
 //BA.debugLineNum = 1421;BA.debugLine="Sub etTiempo_2";
 //BA.debugLineNum = 1422;BA.debugLine="Try";
try { //BA.debugLineNum = 1423;BA.debugLine="ex=True";
_ex = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1424;BA.debugLine="Dim ret As Boolean";
_ret = false;
 //BA.debugLineNum = 1425;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 1426;BA.debugLine="Dim aux_tiempo As  Int";
_aux_tiempo = 0;
 //BA.debugLineNum = 1428;BA.debugLine="Log(\"tipo tiempo Anterior modificaar \"&TIPO_ANTE_L";
anywheresoftware.b4a.keywords.Common.Log("tipo tiempo Anterior modificaar "+mostCurrent._tipo_ante_lv);
 //BA.debugLineNum = 1431;BA.debugLine="Log(\"tipo tiempo Posterior modificaar \"&tIPO_POST_";
anywheresoftware.b4a.keywords.Common.Log("tipo tiempo Posterior modificaar "+mostCurrent._tipo_post_lv);
 //BA.debugLineNum = 1434;BA.debugLine="tiempo_actual=etTiempo.Text";
_tiempo_actual = (int)(Double.parseDouble(mostCurrent._ettiempo.getText()));
 //BA.debugLineNum = 1435;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1436;BA.debugLine="tiempo_actual=tiempo_actual";
_tiempo_actual = _tiempo_actual;
 //BA.debugLineNum = 1437;BA.debugLine="Log(\"Tiempo Actual decimas convertir decimas: \"&ti";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual decimas convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1440;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1441;BA.debugLine="tiempo_actual=tiempo_actual*10";
_tiempo_actual = (int) (_tiempo_actual*10);
 //BA.debugLineNum = 1442;BA.debugLine="Log(\"Tiempo Actual segundos convertir decimas: \"&t";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1444;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1445;BA.debugLine="tiempo_actual=tiempo_actual*600";
_tiempo_actual = (int) (_tiempo_actual*600);
 //BA.debugLineNum = 1446;BA.debugLine="Log(\"Tiempo Actual minutos convertir decimas: \"&ti";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1451;BA.debugLine="If TIPO_ANTE_LV=0 Then";
if ((mostCurrent._tipo_ante_lv).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1452;BA.debugLine="tiempo_ante_lv=tiempo_ante_lv";
mostCurrent._tiempo_ante_lv = mostCurrent._tiempo_ante_lv;
 //BA.debugLineNum = 1453;BA.debugLine="Log(\"Tiempo ant decimas convertir decimas: \"&tiemp";
anywheresoftware.b4a.keywords.Common.Log("Tiempo ant decimas convertir decimas: "+mostCurrent._tiempo_ante_lv);
 //BA.debugLineNum = 1454;BA.debugLine="flag_multi=True";
_flag_multi = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1457;BA.debugLine="If TIPO_ANTE_LV=1 Then";
if ((mostCurrent._tipo_ante_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1458;BA.debugLine="tiempo_ante_lv=tiempo_ante_lv*10";
mostCurrent._tiempo_ante_lv = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo_ante_lv))*10);
 //BA.debugLineNum = 1459;BA.debugLine="Log(\"Tiempo ante segundos convertir decimas: \"&tie";
anywheresoftware.b4a.keywords.Common.Log("Tiempo ante segundos convertir decimas: "+mostCurrent._tiempo_ante_lv);
 };
 //BA.debugLineNum = 1461;BA.debugLine="If TIPO_ANTE_LV=2 Then";
if ((mostCurrent._tipo_ante_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1462;BA.debugLine="tiempo_ante_lv=tiempo_ante_lv*600";
mostCurrent._tiempo_ante_lv = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo_ante_lv))*600);
 //BA.debugLineNum = 1463;BA.debugLine="Log(\"Tiempo ante minutos convertir decimas: \"&tiem";
anywheresoftware.b4a.keywords.Common.Log("Tiempo ante minutos convertir decimas: "+mostCurrent._tiempo_ante_lv);
 //BA.debugLineNum = 1464;BA.debugLine="flag_multi=True";
_flag_multi = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1468;BA.debugLine="If tIPO_POST_LV=0 Then";
if ((mostCurrent._tipo_post_lv).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1469;BA.debugLine="tiempo_post_lv=tiempo_post_lv";
mostCurrent._tiempo_post_lv = mostCurrent._tiempo_post_lv;
 //BA.debugLineNum = 1470;BA.debugLine="Log(\"Tiempo post decimas convertir decimas: \"&tiem";
anywheresoftware.b4a.keywords.Common.Log("Tiempo post decimas convertir decimas: "+mostCurrent._tiempo_post_lv);
 //BA.debugLineNum = 1471;BA.debugLine="flag_multi=True";
_flag_multi = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1474;BA.debugLine="If tIPO_POST_LV=1 Then";
if ((mostCurrent._tipo_post_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1475;BA.debugLine="tiempo_post_lv=tiempo_post_lv*10";
mostCurrent._tiempo_post_lv = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo_post_lv))*10);
 //BA.debugLineNum = 1476;BA.debugLine="Log(\"Tiempo post segundos convertir decimas: \"&tie";
anywheresoftware.b4a.keywords.Common.Log("Tiempo post segundos convertir decimas: "+mostCurrent._tiempo_post_lv);
 };
 //BA.debugLineNum = 1478;BA.debugLine="If tIPO_POST_LV=2 Then";
if ((mostCurrent._tipo_post_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1479;BA.debugLine="tiempo_post_lv=tiempo_post_lv*600";
mostCurrent._tiempo_post_lv = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo_post_lv))*600);
 //BA.debugLineNum = 1480;BA.debugLine="Log(\"Tiempo post minutos convertir decimas: \"&tiem";
anywheresoftware.b4a.keywords.Common.Log("Tiempo post minutos convertir decimas: "+mostCurrent._tiempo_post_lv);
 };
 //BA.debugLineNum = 1484;BA.debugLine="Log(\"tiempo antes de comparar ya convertido \"&tiem";
anywheresoftware.b4a.keywords.Common.Log("tiempo antes de comparar ya convertido "+BA.NumberToString(_tiempo_actual));
 //BA.debugLineNum = 1486;BA.debugLine="If tiempo_actual=tiempo_ante_lv Then";
if (_tiempo_actual==(double)(Double.parseDouble(mostCurrent._tiempo_ante_lv))) { 
 //BA.debugLineNum = 1488;BA.debugLine="ret=mirar_tiempo_servo(servo,tiempo_actual)";
_ret = _mirar_tiempo_servo((int)(Double.parseDouble(mostCurrent._servo)),_tiempo_actual);
 //BA.debugLineNum = 1489;BA.debugLine="Log(\"returnt mismo tiempo servo \"& ret)";
anywheresoftware.b4a.keywords.Common.Log("returnt mismo tiempo servo "+BA.ObjectToString(_ret));
 //BA.debugLineNum = 1490;BA.debugLine="If ret=True Then";
if (_ret==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1491;BA.debugLine="mod_datos_button2";
_mod_datos_button2();
 //BA.debugLineNum = 1492;BA.debugLine="ret=False";
_ret = anywheresoftware.b4a.keywords.Common.False;
 };
 }else if(_tiempo_actual==(double)(Double.parseDouble(mostCurrent._tiempo_post_lv))) { 
 //BA.debugLineNum = 1495;BA.debugLine="Log(\"tiempo =\")";
anywheresoftware.b4a.keywords.Common.Log("tiempo =");
 //BA.debugLineNum = 1496;BA.debugLine="ret=mirar_tiempo_servo(servo,tiempo_actual)";
_ret = _mirar_tiempo_servo((int)(Double.parseDouble(mostCurrent._servo)),_tiempo_actual);
 //BA.debugLineNum = 1497;BA.debugLine="Log(\"returnt mismo tiempo servo \"& ret)";
anywheresoftware.b4a.keywords.Common.Log("returnt mismo tiempo servo "+BA.ObjectToString(_ret));
 //BA.debugLineNum = 1498;BA.debugLine="If ret=True Then";
if (_ret==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1499;BA.debugLine="mod_datos_button2";
_mod_datos_button2();
 //BA.debugLineNum = 1500;BA.debugLine="ret=False";
_ret = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1502;BA.debugLine="ex=False";
_ex = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1506;BA.debugLine="If ex=True Then";
if (_ex==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1508;BA.debugLine="If tiempo_actual>tiempo_ante_lv Then";
if (_tiempo_actual>(double)(Double.parseDouble(mostCurrent._tiempo_ante_lv))) { 
 //BA.debugLineNum = 1510;BA.debugLine="If tiempo_actual<tiempo_post_lv Then";
if (_tiempo_actual<(double)(Double.parseDouble(mostCurrent._tiempo_post_lv))) { 
 //BA.debugLineNum = 1511;BA.debugLine="tiempo=tiempo_actual";
mostCurrent._tiempo = BA.NumberToString(_tiempo_actual);
 //BA.debugLineNum = 1518;BA.debugLine="mod_datos_button2";
_mod_datos_button2();
 }else {
 //BA.debugLineNum = 1520;BA.debugLine="Msgbox(\"Tiempo actual mayor que el posterior!";
anywheresoftware.b4a.keywords.Common.Msgbox("Tiempo actual mayor que el posterior!!!!!","ERROR",mostCurrent.activityBA);
 //BA.debugLineNum = 1521;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1522;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1523;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1524;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1525;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1526;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1527;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1528;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1529;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1530;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1531;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1532;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 }else {
 //BA.debugLineNum = 1536;BA.debugLine="Msgbox(\"Tiempo actual menor que el anterior\",\"ER";
anywheresoftware.b4a.keywords.Common.Msgbox("Tiempo actual menor que el anterior","ERROR",mostCurrent.activityBA);
 //BA.debugLineNum = 1537;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1538;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1539;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1540;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1541;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1542;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1543;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1544;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1545;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1546;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1548;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1549;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1550;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 };
 } 
       catch (Exception e1084) {
			processBA.setLastException(e1084); };
 //BA.debugLineNum = 1559;BA.debugLine="End Sub";
return "";
}
public static String  _ettiempo_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 1124;BA.debugLine="Sub etTiempo_TextChanged (Old As String, New As St";
 //BA.debugLineNum = 1125;BA.debugLine="Try";
try { //BA.debugLineNum = 1126;BA.debugLine="If New>0 Then";
if ((double)(Double.parseDouble(_new))>0) { 
 //BA.debugLineNum = 1127;BA.debugLine="If New<=999 Then";
if ((double)(Double.parseDouble(_new))<=999) { 
 //BA.debugLineNum = 1128;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 1130;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1131;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 };
 };
 } 
       catch (Exception e800) {
			processBA.setLastException(e800); };
 //BA.debugLineNum = 1136;BA.debugLine="End Sub";
return "";
}
public static String  _ficheronuevo() throws Exception{
 //BA.debugLineNum = 2003;BA.debugLine="Sub ficheronuevo";
 //BA.debugLineNum = 2004;BA.debugLine="Try";
try { //BA.debugLineNum = 2005;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 2006;BA.debugLine="spconfig.Enabled=True";
mostCurrent._spconfig.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2007;BA.debugLine="If strspinner=\"PULSE AQUI\" Then";
if ((_strspinner).equals("PULSE AQUI")) { 
 //BA.debugLineNum = 2009;BA.debugLine="spconfig.SelectedIndex=0";
mostCurrent._spconfig.setSelectedIndex((int) (0));
 };
 //BA.debugLineNum = 2012;BA.debugLine="lvDatos.CLEAR";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 2013;BA.debugLine="lbModelo.Text=strfichero";
mostCurrent._lbmodelo.setText((Object)(_strfichero));
 //BA.debugLineNum = 2014;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2015;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2016;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2017;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2018;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2019;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2020;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2021;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2022;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2023;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2024;BA.debugLine="Panel2.Visible=False";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2025;BA.debugLine="Label5.Visible=False";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2026;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2027;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 //BA.debugLineNum = 2028;BA.debugLine="lbNumeroServo.Text=\"STABILO\"";
mostCurrent._lbnumeroservo.setText((Object)("STABILO"));
 //BA.debugLineNum = 2029;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 //BA.debugLineNum = 2030;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 2031;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2032;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2033;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2034;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2035;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2036;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2037;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2038;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2040;BA.debugLine="tiempo_actual=0";
_tiempo_actual = (int) (0);
 } 
       catch (Exception e1403) {
			processBA.setLastException(e1403); };
 //BA.debugLineNum = 2045;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Dim dlgFileExpl As ClsExplorer";
mostCurrent._dlgfileexpl = new com.delozoya.Consola.Completa.clsexplorer();
 //BA.debugLineNum = 22;BA.debugLine="Private btOK As Button";
mostCurrent._btok = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private etFichero As EditText";
mostCurrent._etfichero = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private etGiro As EditText";
mostCurrent._etgiro = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private etTiempo As EditText";
mostCurrent._ettiempo = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lvDatos As ListView";
mostCurrent._lvdatos = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private spconfig As Spinner";
mostCurrent._spconfig = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private spServo As Spinner";
mostCurrent._spservo = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim ex As Boolean";
_ex = false;
 //BA.debugLineNum = 34;BA.debugLine="Dim timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 36;BA.debugLine="Private dial As InputDialog";
mostCurrent._dial = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 38;BA.debugLine="Private spTiempo As Spinner";
mostCurrent._sptiempo = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private Label8 As Label";
mostCurrent._label8 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private Label9 As Label";
mostCurrent._label9 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Dim NOMBRE_FICHERO As String";
mostCurrent._nombre_fichero = "";
 //BA.debugLineNum = 42;BA.debugLine="Dim config As String";
mostCurrent._config = "";
 //BA.debugLineNum = 44;BA.debugLine="Dim di As CustomDialog2";
mostCurrent._di = new anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2();
 //BA.debugLineNum = 51;BA.debugLine="Dim contador As Int";
_contador = 0;
 //BA.debugLineNum = 52;BA.debugLine="Dim buffer_tx(512) As Byte";
_buffer_tx = new byte[(int) (512)];
;
 //BA.debugLineNum = 54;BA.debugLine="Dim valor_d As Byte";
_valor_d = (byte)0;
 //BA.debugLineNum = 55;BA.debugLine="Dim first_time As Boolean";
_first_time = false;
 //BA.debugLineNum = 56;BA.debugLine="Private btEscribirMemoria As Button";
mostCurrent._btescribirmemoria = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private lbrx As Label";
mostCurrent._lbrx = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private bt124 As Button";
mostCurrent._bt124 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private bt1 As Button";
mostCurrent._bt1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private btascii49 As Button";
mostCurrent._btascii49 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Dim flag_d As Boolean";
_flag_d = false;
 //BA.debugLineNum = 62;BA.debugLine="Private btTestAvr As Button";
mostCurrent._bttestavr = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim x As Int";
_x = 0;
 //BA.debugLineNum = 64;BA.debugLine="Dim flag_rx As Boolean";
_flag_rx = false;
 //BA.debugLineNum = 65;BA.debugLine="Dim contador As Int";
_contador = 0;
 //BA.debugLineNum = 67;BA.debugLine="Private btLeer As Button";
mostCurrent._btleer = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Dim listaconfiguracion As List";
mostCurrent._listaconfiguracion = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 76;BA.debugLine="Dim lista1 As List";
mostCurrent._lista1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 77;BA.debugLine="Dim contador_lineas As Int";
_contador_lineas = 0;
 //BA.debugLineNum = 78;BA.debugLine="Dim servo As String";
mostCurrent._servo = "";
 //BA.debugLineNum = 79;BA.debugLine="Dim giro As String";
mostCurrent._giro = "";
 //BA.debugLineNum = 80;BA.debugLine="Dim tiempo As String";
mostCurrent._tiempo = "";
 //BA.debugLineNum = 81;BA.debugLine="Dim tipo_tiempo As String";
mostCurrent._tipo_tiempo = "";
 //BA.debugLineNum = 83;BA.debugLine="Dim servo_lv As String";
mostCurrent._servo_lv = "";
 //BA.debugLineNum = 84;BA.debugLine="Dim tiempo_lv As String";
mostCurrent._tiempo_lv = "";
 //BA.debugLineNum = 85;BA.debugLine="Dim tipo_tiempo_lv As String";
mostCurrent._tipo_tiempo_lv = "";
 //BA.debugLineNum = 86;BA.debugLine="Dim giro_lv As String";
mostCurrent._giro_lv = "";
 //BA.debugLineNum = 89;BA.debugLine="Dim tiempo_aux As String";
mostCurrent._tiempo_aux = "";
 //BA.debugLineNum = 90;BA.debugLine="Dim tipo_tiempo_aux As String";
mostCurrent._tipo_tiempo_aux = "";
 //BA.debugLineNum = 91;BA.debugLine="Dim tipo_tiempo_aux1 As String";
mostCurrent._tipo_tiempo_aux1 = "";
 //BA.debugLineNum = 93;BA.debugLine="Dim giro_aux As String";
mostCurrent._giro_aux = "";
 //BA.debugLineNum = 94;BA.debugLine="Dim servo_aux As String";
mostCurrent._servo_aux = "";
 //BA.debugLineNum = 97;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 98;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 99;BA.debugLine="Private lbNumeroServo As Label";
mostCurrent._lbnumeroservo = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 100;BA.debugLine="Private lbModelo As Label";
mostCurrent._lbmodelo = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 101;BA.debugLine="Private Label10 As Label";
mostCurrent._label10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 102;BA.debugLine="Dim posicion_mod As String";
mostCurrent._posicion_mod = "";
 //BA.debugLineNum = 104;BA.debugLine="Dim tiempo_ante_lv As String";
mostCurrent._tiempo_ante_lv = "";
 //BA.debugLineNum = 105;BA.debugLine="Dim tiempo_post_lv As String";
mostCurrent._tiempo_post_lv = "";
 //BA.debugLineNum = 106;BA.debugLine="Dim servo_ante_lv As String";
mostCurrent._servo_ante_lv = "";
 //BA.debugLineNum = 107;BA.debugLine="Dim servo_post_lv As String";
mostCurrent._servo_post_lv = "";
 //BA.debugLineNum = 108;BA.debugLine="Dim TIPO_ANTE_LV As String";
mostCurrent._tipo_ante_lv = "";
 //BA.debugLineNum = 109;BA.debugLine="Dim tIPO_POST_LV As String";
mostCurrent._tipo_post_lv = "";
 //BA.debugLineNum = 110;BA.debugLine="Dim flag_multi As Boolean";
_flag_multi = false;
 //BA.debugLineNum = 114;BA.debugLine="Dim flag_button2 As Boolean=False";
_flag_button2 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 116;BA.debugLine="Private Button2 As Button";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 119;BA.debugLine="Dim valor_inicial As Int";
_valor_inicial = 0;
 //BA.debugLineNum = 120;BA.debugLine="Dim valor_final As Int";
_valor_final = 0;
 //BA.debugLineNum = 121;BA.debugLine="Dim numero_suma As Int";
_numero_suma = 0;
 //BA.debugLineNum = 122;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 123;BA.debugLine="Dim BC As ColorDrawable";
mostCurrent._bc = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 124;BA.debugLine="Dim BC1 As ColorDrawable";
mostCurrent._bc1 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 125;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 126;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 127;BA.debugLine="Private Panel3 As Panel";
mostCurrent._panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 128;BA.debugLine="Private Panel4 As Panel";
mostCurrent._panel4 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 129;BA.debugLine="Dim spconfig1 As Spinner";
mostCurrent._spconfig1 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 130;BA.debugLine="Private btComenzar As Button";
mostCurrent._btcomenzar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 131;BA.debugLine="Private btSalir As Button";
mostCurrent._btsalir = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 133;BA.debugLine="Dim ime1 As IME";
mostCurrent._ime1 = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 135;BA.debugLine="Dim tiempo_minimo As Int";
_tiempo_minimo = 0;
 //BA.debugLineNum = 136;BA.debugLine="Dim tiempo_actual As Int";
_tiempo_actual = 0;
 //BA.debugLineNum = 137;BA.debugLine="Dim tiempo_repite As Boolean";
_tiempo_repite = false;
 //BA.debugLineNum = 138;BA.debugLine="Private Label5 As Label";
mostCurrent._label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 139;BA.debugLine="Private btgrabar As Button";
mostCurrent._btgrabar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 140;BA.debugLine="Dim servo_aux_nombre As String";
mostCurrent._servo_aux_nombre = "";
 //BA.debugLineNum = 142;BA.debugLine="Private lbservotxt As Label";
mostCurrent._lbservotxt = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 144;BA.debugLine="Dim tim As Timer";
mostCurrent._tim = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 145;BA.debugLine="Private btLeer As Button";
mostCurrent._btleer = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 146;BA.debugLine="Private cbled As CheckBox";
mostCurrent._cbled = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 147;BA.debugLine="Dim nombre_leido As String";
mostCurrent._nombre_leido = "";
 //BA.debugLineNum = 150;BA.debugLine="Dim flag_lectura As Boolean=False";
_flag_lectura = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 151;BA.debugLine="Dim t As Int=0";
_t = (int) (0);
 //BA.debugLineNum = 153;BA.debugLine="Dim flag_inicio_usb As Boolean=False";
_flag_inicio_usb = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return "";
}
public static String  _grabar() throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
String _servo_nombre = "";
int _i = 0;
 //BA.debugLineNum = 2231;BA.debugLine="Sub grabar";
 //BA.debugLineNum = 2232;BA.debugLine="Try";
try { //BA.debugLineNum = 2233;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 2234;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 2235;BA.debugLine="Dim servo_nombre As String";
_servo_nombre = "";
 //BA.debugLineNum = 2236;BA.debugLine="If contador_lineas<254 Then";
if (_contador_lineas<254) { 
 //BA.debugLineNum = 2237;BA.debugLine="If contador_lineas>=22 Then";
if (_contador_lineas>=22) { 
 //BA.debugLineNum = 2240;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 2241;BA.debugLine="aux_valor_tiempo=\"00\"&tiempo";
_aux_valor_tiempo = "00"+mostCurrent._tiempo;
 //BA.debugLineNum = 2242;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 2246;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 2247;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 2248;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 2249;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 2254;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 2256;BA.debugLine="aux_valor_tiempo=tiempo";
_aux_valor_tiempo = mostCurrent._tiempo;
 //BA.debugLineNum = 2257;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 2263;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 2265;BA.debugLine="lista1.Add(\"0\")";
mostCurrent._lista1.Add((Object)("0"));
 };
 //BA.debugLineNum = 2267;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 2269;BA.debugLine="lista1.Add(\"1\")";
mostCurrent._lista1.Add((Object)("1"));
 };
 //BA.debugLineNum = 2271;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 2273;BA.debugLine="lista1.Add(\"2\")";
mostCurrent._lista1.Add((Object)("2"));
 };
 //BA.debugLineNum = 2276;BA.debugLine="lista1.Add(servo)";
mostCurrent._lista1.Add((Object)(mostCurrent._servo));
 //BA.debugLineNum = 2277;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2278;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 2279;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2283;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2284;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2285;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2286;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2291;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2292;BA.debugLine="aux_valor_giro=giro";
_aux_valor_giro = mostCurrent._giro;
 //BA.debugLineNum = 2294;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2298;BA.debugLine="log_lista";
_log_lista();
 //BA.debugLineNum = 2299;BA.debugLine="contador_lineas=contador_lineas+4";
_contador_lineas = (int) (_contador_lineas+4);
 //BA.debugLineNum = 2300;BA.debugLine="Log(\"numero lineas  \"&(contador_lineas-2))";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString((_contador_lineas-2)));
 };
 }else {
 //BA.debugLineNum = 2308;BA.debugLine="ToastMessageShow(\"Maximo lineas\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Maximo lineas",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2309;BA.debugLine="Log(\"numero lineas  \"&(contador_lineas-2))";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString((_contador_lineas-2)));
 //BA.debugLineNum = 2310;BA.debugLine="Log(\"------------------------------\")";
anywheresoftware.b4a.keywords.Common.Log("------------------------------");
 //BA.debugLineNum = 2311;BA.debugLine="Log(\"------------------------------\")";
anywheresoftware.b4a.keywords.Common.Log("------------------------------");
 //BA.debugLineNum = 2313;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step1465 = 1;
final int limit1465 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0); (step1465 > 0 && _i <= limit1465) || (step1465 < 0 && _i >= limit1465); _i = ((int)(0 + _i + step1465))) {
 //BA.debugLineNum = 2315;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 };
 //BA.debugLineNum = 2319;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2321;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2322;BA.debugLine="servo_nombre=\"INC.STABILO 1\"";
_servo_nombre = "INC.STABILO 1";
 };
 //BA.debugLineNum = 2324;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 2325;BA.debugLine="servo_nombre=\"DIRECCION   2\"";
_servo_nombre = "DIRECCION   2";
 };
 //BA.debugLineNum = 2327;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 2328;BA.debugLine="servo_nombre=\"C.INCI.ALAS 3\"";
_servo_nombre = "C.INCI.ALAS 3";
 };
 //BA.debugLineNum = 2330;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 2331;BA.debugLine="servo_nombre=\"FLAPPER     4\"";
_servo_nombre = "FLAPPER     4";
 };
 //BA.debugLineNum = 2333;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 2334;BA.debugLine="servo_nombre=\"GANCHO/FLAP 5\"";
_servo_nombre = "GANCHO/FLAP 5";
 };
 //BA.debugLineNum = 2339;BA.debugLine="lvDatos.AddSingleLine(servo_nombre&\"   Giro: \"&aux";
mostCurrent._lvdatos.AddSingleLine(_servo_nombre+"   Giro: "+_aux_valor_giro+"    Tiempo: "+_aux_valor_tiempo+"  Tipo tiempo: "+mostCurrent._tipo_tiempo);
 //BA.debugLineNum = 2340;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 2344;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 2345;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 2346;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 2347;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 2348;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 2349;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 2350;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 2351;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2352;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2353;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2354;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2355;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e1500) {
			processBA.setLastException(e1500); };
 //BA.debugLineNum = 2360;BA.debugLine="End Sub";
return "";
}
public static String  _initpanel(anywheresoftware.b4a.objects.PanelWrapper _pnl,float _borderwidth,int _fillcolor,int _bordercolor) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rec = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas1 = null;
float _borderwidth_2 = 0f;
 //BA.debugLineNum = 3277;BA.debugLine="Sub InitPanel(pnl As Panel,BorderWidth As Float, F";
 //BA.debugLineNum = 3278;BA.debugLine="Dim Rec As Rect";
_rec = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 3279;BA.debugLine="Dim Canvas1 As Canvas";
_canvas1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 3280;BA.debugLine="Dim BorderWidth_2 As Float";
_borderwidth_2 = 0f;
 //BA.debugLineNum = 3282;BA.debugLine="BorderWidth_2=BorderWidth/2";
_borderwidth_2 = (float) (_borderwidth/(double)2);
 //BA.debugLineNum = 3283;BA.debugLine="Rec.Initialize(BorderWidth_2,BorderWidth_2,pnl.Wi";
_rec.Initialize((int) (_borderwidth_2),(int) (_borderwidth_2),(int) (_pnl.getWidth()-_borderwidth_2),(int) (_pnl.getHeight()-_borderwidth_2));
 //BA.debugLineNum = 3284;BA.debugLine="Canvas1.Initialize(pnl)";
_canvas1.Initialize((android.view.View)(_pnl.getObject()));
 //BA.debugLineNum = 3285;BA.debugLine="Canvas1.DrawRect(Rec,FillColor,True,FillColor)";
_canvas1.DrawRect((android.graphics.Rect)(_rec.getObject()),_fillcolor,anywheresoftware.b4a.keywords.Common.True,(float) (_fillcolor));
 //BA.debugLineNum = 3286;BA.debugLine="Canvas1.DrawRect(Rec,BorderColor,False,BorderWidt";
_canvas1.DrawRect((android.graphics.Rect)(_rec.getObject()),_bordercolor,anywheresoftware.b4a.keywords.Common.False,_borderwidth);
 //BA.debugLineNum = 3287;BA.debugLine="End Sub";
return "";
}
public static String  _lbmodelo_longclick() throws Exception{
 //BA.debugLineNum = 2838;BA.debugLine="Sub lbModelo_LongClick";
 //BA.debugLineNum = 2840;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 2842;BA.debugLine="dlgFileExpl.Initialize(Activity, \"/mnt/sdcard/Cons";
mostCurrent._dlgfileexpl._initialize(mostCurrent.activityBA,mostCurrent._activity,"/mnt/sdcard/Consola/F1A","",anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False,"OK");
 //BA.debugLineNum = 2843;BA.debugLine="dlgFileExpl.BorderColor = Colors.RGB(128, 128, 12";
mostCurrent._dlgfileexpl._bordercolor = anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (128),(int) (128),(int) (128));
 //BA.debugLineNum = 2845;BA.debugLine="dlgFileExpl.FolderTextColor = Colors.Black";
mostCurrent._dlgfileexpl._foldertextcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
 //BA.debugLineNum = 2846;BA.debugLine="dlgFileExpl.FileTextColor1 = Colors.black";
mostCurrent._dlgfileexpl._filetextcolor1 = anywheresoftware.b4a.keywords.Common.Colors.Black;
 //BA.debugLineNum = 2848;BA.debugLine="dlgFileExpl.Explorer";
mostCurrent._dlgfileexpl._explorer();
 //BA.debugLineNum = 2849;BA.debugLine="If Not(dlgFileExpl.Selection.Canceled Or dlgFileE";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._dlgfileexpl._selection.Canceled || (mostCurrent._dlgfileexpl._selection.ChosenPath).equals(""))) { 
 };
 //BA.debugLineNum = 2855;BA.debugLine="End Sub";
return "";
}
public static String  _leer_fichero() throws Exception{
int _i = 0;
String _aux = "";
byte[] _b = null;
 //BA.debugLineNum = 3564;BA.debugLine="Sub Leer_fichero";
 //BA.debugLineNum = 3569;BA.debugLine="For i=0 To lista1.Size-1";
{
final int step2286 = 1;
final int limit2286 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0); (step2286 > 0 && _i <= limit2286) || (step2286 < 0 && _i >= limit2286); _i = ((int)(0 + _i + step2286))) {
 //BA.debugLineNum = 3571;BA.debugLine="buffer_tx(i)=lista1.Get(i)";
_buffer_tx[_i] = (byte)(BA.ObjectToNumber(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 3574;BA.debugLine="For i=lista1.Size To 255";
{
final int step2289 = 1;
final int limit2289 = (int) (255);
for (_i = mostCurrent._lista1.getSize(); (step2289 > 0 && _i <= limit2289) || (step2289 < 0 && _i >= limit2289); _i = ((int)(0 + _i + step2289))) {
 //BA.debugLineNum = 3575;BA.debugLine="buffer_tx(i)=0";
_buffer_tx[_i] = (byte) (0);
 }
};
 //BA.debugLineNum = 3579;BA.debugLine="buffer_tx(222)=1";
_buffer_tx[(int) (222)] = (byte) (1);
 //BA.debugLineNum = 3580;BA.debugLine="buffer_tx(223)=listaconfiguracion.Get(0)";
_buffer_tx[(int) (223)] = (byte)(BA.ObjectToNumber(mostCurrent._listaconfiguracion.Get((int) (0))));
 //BA.debugLineNum = 3581;BA.debugLine="Dim aux As String";
_aux = "";
 //BA.debugLineNum = 3582;BA.debugLine="Dim b() As Byte";
_b = new byte[(int) (0)];
;
 //BA.debugLineNum = 3583;BA.debugLine="aux=listaconfiguracion.Get(1)";
_aux = BA.ObjectToString(mostCurrent._listaconfiguracion.Get((int) (1)));
 //BA.debugLineNum = 3584;BA.debugLine="b= aux.GetBytes(\"UTF-8\")";
_b = _aux.getBytes("UTF-8");
 //BA.debugLineNum = 3585;BA.debugLine="For i=0 To b.Length-1";
{
final int step2298 = 1;
final int limit2298 = (int) (_b.length-1);
for (_i = (int) (0); (step2298 > 0 && _i <= limit2298) || (step2298 < 0 && _i >= limit2298); _i = ((int)(0 + _i + step2298))) {
 //BA.debugLineNum = 3586;BA.debugLine="buffer_tx(i+224)=b(i)";
_buffer_tx[(int) (_i+224)] = _b[_i];
 }
};
 //BA.debugLineNum = 3590;BA.debugLine="For i=0 To 255";
{
final int step2301 = 1;
final int limit2301 = (int) (255);
for (_i = (int) (0); (step2301 > 0 && _i <= limit2301) || (step2301 < 0 && _i >= limit2301); _i = ((int)(0 + _i + step2301))) {
 //BA.debugLineNum = 3591;BA.debugLine="Log(buffer_tx(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_buffer_tx[_i]));
 }
};
 //BA.debugLineNum = 3594;BA.debugLine="Log( \"FICHERO DE DATOS LEIDO\")";
anywheresoftware.b4a.keywords.Common.Log("FICHERO DE DATOS LEIDO");
 //BA.debugLineNum = 3596;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
com.delozoya.Consola.Completa.customlistview _lv = null;
 //BA.debugLineNum = 3270;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 3271;BA.debugLine="Dim lv As CustomListView";
_lv = new com.delozoya.Consola.Completa.customlistview();
 //BA.debugLineNum = 3272;BA.debugLine="ListView1.SetSelection(Position)";
mostCurrent._listview1.SetSelection(_position);
 //BA.debugLineNum = 3273;BA.debugLine="lvDatos.SetSelection(Position)";
mostCurrent._lvdatos.SetSelection(_position);
 //BA.debugLineNum = 3275;BA.debugLine="End Sub";
return "";
}
public static String  _log_lista() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2750;BA.debugLine="Sub log_lista";
 //BA.debugLineNum = 2751;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step1766 = 1;
final int limit1766 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0); (step1766 > 0 && _i <= limit1766) || (step1766 < 0 && _i >= limit1766); _i = ((int)(0 + _i + step1766))) {
 //BA.debugLineNum = 2753;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 2758;BA.debugLine="End Sub";
return "";
}
public static String  _lvdatos_itemclick(int _position,Object _value) throws Exception{
String _str = "";
String _lv_tipo = "";
int _lv_tipo1 = 0;
 //BA.debugLineNum = 426;BA.debugLine="Sub lvDatos_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 427;BA.debugLine="Try";
try { //BA.debugLineNum = 428;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 429;BA.debugLine="tiempo_ante_lv=0";
mostCurrent._tiempo_ante_lv = BA.NumberToString(0);
 //BA.debugLineNum = 430;BA.debugLine="Button2.Enabled=True";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 431;BA.debugLine="Button2.Visible=True";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 432;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 433;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 434;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 435;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 436;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 437;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 438;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 439;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 440;BA.debugLine="etTiempo.Enabled=True";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 441;BA.debugLine="spTiempo.Enabled=True";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 442;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 443;BA.debugLine="Dim lv_tipo As String";
_lv_tipo = "";
 //BA.debugLineNum = 444;BA.debugLine="Dim lv_tipo1  As Int";
_lv_tipo1 = 0;
 //BA.debugLineNum = 448;BA.debugLine="If Position<=4 Then";
if (_position<=4) { 
 //BA.debugLineNum = 449;BA.debugLine="Button2.Enabled=True";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 450;BA.debugLine="Button2.Visible=True";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 451;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 452;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 453;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 454;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 455;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 456;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 457;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 458;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 459;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 460;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 461;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 462;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 463;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 465;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 466;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 467;BA.debugLine="servo_lv=str.SubString2(12,13)";
mostCurrent._servo_lv = _str.substring((int) (12),(int) (13));
 //BA.debugLineNum = 468;BA.debugLine="giro_lv=str.SubString2(22,25)";
mostCurrent._giro_lv = _str.substring((int) (22),(int) (25));
 //BA.debugLineNum = 469;BA.debugLine="lbNumeroServo.text=servo_lv";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo_lv));
 //BA.debugLineNum = 471;BA.debugLine="lbNumeroServo.text=servo_lv";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo_lv));
 //BA.debugLineNum = 473;BA.debugLine="If servo_lv=1 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 474;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 476;BA.debugLine="If servo_lv=2 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 477;BA.debugLine="lbservotxt.Text=\"DIRECCION\"";
mostCurrent._lbservotxt.setText((Object)("DIRECCION"));
 };
 //BA.debugLineNum = 479;BA.debugLine="If servo_lv=3 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 480;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 482;BA.debugLine="If servo_lv=4 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 483;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 485;BA.debugLine="If servo_lv=5 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 486;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 //BA.debugLineNum = 492;BA.debugLine="Log(\"giro_lv: \"&giro_lv)";
anywheresoftware.b4a.keywords.Common.Log("giro_lv: "+mostCurrent._giro_lv);
 //BA.debugLineNum = 493;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 494;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 498;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 499;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 500;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 505;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 507;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 515;BA.debugLine="flag_button2=True";
_flag_button2 = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 524;BA.debugLine="If Position>=5 Then";
if (_position>=5) { 
 //BA.debugLineNum = 525;BA.debugLine="If Position<=9 Then";
if (_position<=9) { 
 //BA.debugLineNum = 526;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 527;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 528;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 529;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 530;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 531;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 532;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 533;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 534;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 535;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 536;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 537;BA.debugLine="Label9.Text=\"RDT\"";
mostCurrent._label9.setText((Object)("RDT"));
 //BA.debugLineNum = 538;BA.debugLine="Label9.Visible=True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 539;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 541;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 542;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 543;BA.debugLine="servo_lv=str.SubString2(12,13)";
mostCurrent._servo_lv = _str.substring((int) (12),(int) (13));
 //BA.debugLineNum = 544;BA.debugLine="giro_lv=str.SubString2(22,25)";
mostCurrent._giro_lv = _str.substring((int) (22),(int) (25));
 //BA.debugLineNum = 546;BA.debugLine="If servo_lv=1 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 547;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 549;BA.debugLine="If servo_lv=2 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 550;BA.debugLine="lbservotxt.Text=\"DIRECCION\"";
mostCurrent._lbservotxt.setText((Object)("DIRECCION"));
 };
 //BA.debugLineNum = 552;BA.debugLine="If servo_lv=3 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 553;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 555;BA.debugLine="If servo_lv=4 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 556;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 558;BA.debugLine="If servo_lv=5 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 559;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 //BA.debugLineNum = 566;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 567;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 570;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 571;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 572;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 577;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 579;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 586;BA.debugLine="flag_button2=True";
_flag_button2 = anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 599;BA.debugLine="If Position>=10 Then";
if (_position>=10) { 
 //BA.debugLineNum = 600;BA.debugLine="If Position<=14 Then";
if (_position<=14) { 
 //BA.debugLineNum = 601;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 602;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 603;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 604;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 605;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 606;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 607;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 608;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 609;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 610;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 611;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 612;BA.debugLine="Label9.Visible=True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 613;BA.debugLine="Label9.Text=\"R.Circular\"";
mostCurrent._label9.setText((Object)("R.Circular"));
 //BA.debugLineNum = 614;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 617;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 618;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 619;BA.debugLine="servo_lv=str.SubString2(12,13)";
mostCurrent._servo_lv = _str.substring((int) (12),(int) (13));
 //BA.debugLineNum = 620;BA.debugLine="giro_lv=str.SubString2(22,25)";
mostCurrent._giro_lv = _str.substring((int) (22),(int) (25));
 //BA.debugLineNum = 621;BA.debugLine="lbNumeroServo.text=servo_lv";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo_lv));
 //BA.debugLineNum = 622;BA.debugLine="If servo_lv=1 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 623;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 625;BA.debugLine="If servo_lv=2 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 626;BA.debugLine="lbservotxt.Text=\"DIRECCION\"";
mostCurrent._lbservotxt.setText((Object)("DIRECCION"));
 };
 //BA.debugLineNum = 628;BA.debugLine="If servo_lv=3 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 629;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 631;BA.debugLine="If servo_lv=4 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 632;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 634;BA.debugLine="If servo_lv=5 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 635;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 //BA.debugLineNum = 638;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 639;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 642;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 643;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 644;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 649;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 651;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 658;BA.debugLine="flag_button2=True";
_flag_button2 = anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 666;BA.debugLine="If Position>=15 Then";
if (_position>=15) { 
 //BA.debugLineNum = 667;BA.debugLine="If Position<=19 Then";
if (_position<=19) { 
 //BA.debugLineNum = 668;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 669;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 670;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 671;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 672;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 673;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 674;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 675;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 676;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 677;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 678;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 679;BA.debugLine="Label9.Visible=True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 680;BA.debugLine="Label9.Text=\"PRE-START\"";
mostCurrent._label9.setText((Object)("PRE-START"));
 //BA.debugLineNum = 681;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 683;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 684;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 685;BA.debugLine="servo_lv=str.SubString2(12,13)";
mostCurrent._servo_lv = _str.substring((int) (12),(int) (13));
 //BA.debugLineNum = 686;BA.debugLine="giro_lv=str.SubString2(22,25)";
mostCurrent._giro_lv = _str.substring((int) (22),(int) (25));
 //BA.debugLineNum = 687;BA.debugLine="lbNumeroServo.text=servo_lv";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo_lv));
 //BA.debugLineNum = 688;BA.debugLine="If servo_lv=1 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 689;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 691;BA.debugLine="If servo_lv=2 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 692;BA.debugLine="lbservotxt.Text=\"DIRECCION\"";
mostCurrent._lbservotxt.setText((Object)("DIRECCION"));
 };
 //BA.debugLineNum = 694;BA.debugLine="If servo_lv=3 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 695;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 697;BA.debugLine="If servo_lv=4 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 698;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 700;BA.debugLine="If servo_lv=5 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 701;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 //BA.debugLineNum = 704;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 705;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 708;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 709;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 710;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 715;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 717;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 724;BA.debugLine="flag_button2=True";
_flag_button2 = anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 731;BA.debugLine="If Position>=20 Then";
if (_position>=20) { 
 //BA.debugLineNum = 732;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 733;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 734;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 735;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 736;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 737;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 738;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 739;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 740;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 741;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 742;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 746;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 747;BA.debugLine="mod_tiempo(Position)";
_mod_tiempo(_position);
 //BA.debugLineNum = 749;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 752;BA.debugLine="lv_tipo=str.SubString2(55,56)";
_lv_tipo = _str.substring((int) (55),(int) (56));
 //BA.debugLineNum = 755;BA.debugLine="If lv_tipo=\"D\" Then";
if ((_lv_tipo).equals("D")) { 
 //BA.debugLineNum = 756;BA.debugLine="lv_tipo1=1";
_lv_tipo1 = (int) (1);
 //BA.debugLineNum = 757;BA.debugLine="tipo_tiempo=\"DECIMAS\"";
mostCurrent._tipo_tiempo = "DECIMAS";
 };
 //BA.debugLineNum = 760;BA.debugLine="If lv_tipo=\"S\" Then";
if ((_lv_tipo).equals("S")) { 
 //BA.debugLineNum = 761;BA.debugLine="lv_tipo1=2";
_lv_tipo1 = (int) (2);
 //BA.debugLineNum = 762;BA.debugLine="tipo_tiempo=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo = "SEGUNDOS";
 };
 //BA.debugLineNum = 765;BA.debugLine="If lv_tipo=\"M\" Then";
if ((_lv_tipo).equals("M")) { 
 //BA.debugLineNum = 766;BA.debugLine="lv_tipo1=3";
_lv_tipo1 = (int) (3);
 //BA.debugLineNum = 767;BA.debugLine="tipo_tiempo=\"MINUTOS\"";
mostCurrent._tipo_tiempo = "MINUTOS";
 };
 //BA.debugLineNum = 770;BA.debugLine="tiempo_lv =str.SubString2(37,40)";
mostCurrent._tiempo_lv = _str.substring((int) (37),(int) (40));
 //BA.debugLineNum = 771;BA.debugLine="etTiempo.Text=tiempo_lv";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv));
 //BA.debugLineNum = 773;BA.debugLine="If tiempo_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))<=9) { 
 //BA.debugLineNum = 774;BA.debugLine="etTiempo.Text=tiempo_lv.SubString(2)";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 777;BA.debugLine="If tiempo_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))<=99) { 
 //BA.debugLineNum = 778;BA.debugLine="If tiempo_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))>9) { 
 //BA.debugLineNum = 779;BA.debugLine="etTiempo.Text=tiempo_lv.SubString2(1,3)";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 784;BA.debugLine="If tiempo_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))>99) { 
 //BA.debugLineNum = 786;BA.debugLine="etTiempo.Text=tiempo_lv";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv));
 };
 //BA.debugLineNum = 791;BA.debugLine="servo_lv=str.SubString2(12,13)";
mostCurrent._servo_lv = _str.substring((int) (12),(int) (13));
 //BA.debugLineNum = 792;BA.debugLine="servo=servo_lv";
mostCurrent._servo = mostCurrent._servo_lv;
 //BA.debugLineNum = 793;BA.debugLine="Log(\"Esto es el servo \"&servo_lv)";
anywheresoftware.b4a.keywords.Common.Log("Esto es el servo "+mostCurrent._servo_lv);
 //BA.debugLineNum = 795;BA.debugLine="lbNumeroServo.Text=servo";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo));
 //BA.debugLineNum = 796;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 797;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 799;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 800;BA.debugLine="lbservotxt.Text=\"DIRECCION\"";
mostCurrent._lbservotxt.setText((Object)("DIRECCION"));
 };
 //BA.debugLineNum = 802;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 803;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 805;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 806;BA.debugLine="lbservotxt.Text=\"FLAPPER\"";
mostCurrent._lbservotxt.setText((Object)("FLAPPER"));
 };
 //BA.debugLineNum = 808;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 809;BA.debugLine="lbservotxt.Text=\"GANCHO/FLAP\"";
mostCurrent._lbservotxt.setText((Object)("GANCHO/FLAP"));
 };
 //BA.debugLineNum = 811;BA.debugLine="spServo.SelectedIndex=servo_lv";
mostCurrent._spservo.setSelectedIndex((int)(Double.parseDouble(mostCurrent._servo_lv)));
 //BA.debugLineNum = 813;BA.debugLine="spTiempo.SelectedIndex=lv_tipo1";
mostCurrent._sptiempo.setSelectedIndex(_lv_tipo1);
 //BA.debugLineNum = 816;BA.debugLine="giro_lv=str.SubString2(22,25)";
mostCurrent._giro_lv = _str.substring((int) (22),(int) (25));
 //BA.debugLineNum = 817;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 818;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 821;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 822;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 823;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 828;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 830;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 834;BA.debugLine="tipo_tiempo_aux1=str.SubString2(55,56)";
mostCurrent._tipo_tiempo_aux1 = _str.substring((int) (55),(int) (56));
 };
 } 
       catch (Exception e604) {
			processBA.setLastException(e604); };
 //BA.debugLineNum = 854;BA.debugLine="End Sub";
return "";
}
public static String  _lvdatos_itemlongclick(int _position,Object _value) throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
int _i = 0;
int _test = 0;
 //BA.debugLineNum = 1023;BA.debugLine="Sub lvDatos_ItemLongClick (Position As Int, Value";
 //BA.debugLineNum = 1025;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 1026;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 1027;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 1028;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 1029;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 1030;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 1031;BA.debugLine="If Position<20 Then";
if (_position<20) { 
 }else {
 //BA.debugLineNum = 1036;BA.debugLine="lvDatos.RemoveAt(Position)";
mostCurrent._lvdatos.RemoveAt(_position);
 //BA.debugLineNum = 1040;BA.debugLine="If posicion_mod<30 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<30) { 
 //BA.debugLineNum = 1041;BA.debugLine="If posicion_mod>=20 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=20) { 
 //BA.debugLineNum = 1042;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1043;BA.debugLine="pos_valor=(pos_mod_aux*4)+20 'AQUI NO 10 SI NO 2";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+20);
 //BA.debugLineNum = 1045;BA.debugLine="If posicion_mod= lvDatos.Size Then";
if ((mostCurrent._posicion_mod).equals(BA.NumberToString(mostCurrent._lvdatos.getSize()))) { 
 //BA.debugLineNum = 1046;BA.debugLine="For i=0 To 3";
{
final int step739 = 1;
final int limit739 = (int) (3);
for (_i = (int) (0); (step739 > 0 && _i <= limit739) || (step739 < 0 && _i >= limit739); _i = ((int)(0 + _i + step739))) {
 //BA.debugLineNum = 1047;BA.debugLine="lista1.RemoveAt(pos_valor-1)";
mostCurrent._lista1.RemoveAt((int) (_pos_valor-1));
 }
};
 }else {
 //BA.debugLineNum = 1051;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 1052;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 1053;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 1054;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 };
 };
 };
 //BA.debugLineNum = 1066;BA.debugLine="borrar_datos(30,40,60)";
_borrar_datos((int) (30),(int) (40),(int) (60));
 //BA.debugLineNum = 1068;BA.debugLine="borrar_datos(40,50,100)";
_borrar_datos((int) (40),(int) (50),(int) (100));
 //BA.debugLineNum = 1070;BA.debugLine="borrar_datos(50,60,140)";
_borrar_datos((int) (50),(int) (60),(int) (140));
 //BA.debugLineNum = 1072;BA.debugLine="borrar_datos(60,70,180)";
_borrar_datos((int) (60),(int) (70),(int) (180));
 //BA.debugLineNum = 1075;BA.debugLine="Dim test As Int";
_test = 0;
 //BA.debugLineNum = 1077;BA.debugLine="For i=0 To lista1.Size-1";
{
final int step755 = 1;
final int limit755 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0); (step755 > 0 && _i <= limit755) || (step755 < 0 && _i >= limit755); _i = ((int)(0 + _i + step755))) {
 //BA.debugLineNum = 1079;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 1082;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 1083;BA.debugLine="If Position=20 Then";
if (_position==20) { 
 //BA.debugLineNum = 1084;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 }else {
 //BA.debugLineNum = 1086;BA.debugLine="tiempo_minimo=lista1.Get(lista1.Size-4)";
_tiempo_minimo = (int)(BA.ObjectToNumber(mostCurrent._lista1.Get((int) (mostCurrent._lista1.getSize()-4))));
 //BA.debugLineNum = 1088;BA.debugLine="test=lista1.Get(lista1.Size-3)";
_test = (int)(BA.ObjectToNumber(mostCurrent._lista1.Get((int) (mostCurrent._lista1.getSize()-3))));
 //BA.debugLineNum = 1091;BA.debugLine="If test=0 Then";
if (_test==0) { 
 //BA.debugLineNum = 1092;BA.debugLine="tiempo_minimo=tiempo_minimo";
_tiempo_minimo = _tiempo_minimo;
 };
 //BA.debugLineNum = 1096;BA.debugLine="If test=1 Then";
if (_test==1) { 
 //BA.debugLineNum = 1097;BA.debugLine="tiempo_minimo=tiempo_minimo*10";
_tiempo_minimo = (int) (_tiempo_minimo*10);
 };
 //BA.debugLineNum = 1101;BA.debugLine="If test=2 Then";
if (_test==2) { 
 //BA.debugLineNum = 1102;BA.debugLine="tiempo_minimo=tiempo_minimo*600";
_tiempo_minimo = (int) (_tiempo_minimo*600);
 };
 };
 //BA.debugLineNum = 1106;BA.debugLine="Log(\"Ultimo tiemoi \"&tiempo_minimo)";
anywheresoftware.b4a.keywords.Common.Log("Ultimo tiemoi "+BA.NumberToString(_tiempo_minimo));
 };
 //BA.debugLineNum = 1109;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1110;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1111;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1112;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1113;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1114;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1115;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1116;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1117;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1118;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1119;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1120;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1122;BA.debugLine="End Sub";
return "";
}
public static boolean  _mirar_tiempo_servo(int _servo_actual,int _tiempo_actual1) throws Exception{
int _numerolineas = 0;
int _aux_servo = 0;
int _aux_tiempo = 0;
String _aux_tipo = "";
int _i = 0;
String _str = "";
int _num = 0;
String _servo_actual_nombre = "";
 //BA.debugLineNum = 1831;BA.debugLine="Sub mirar_tiempo_servo(servo_actual As Int, tiempo";
 //BA.debugLineNum = 1832;BA.debugLine="Dim numerolineas As Int";
_numerolineas = 0;
 //BA.debugLineNum = 1833;BA.debugLine="Dim aux_servo As Int";
_aux_servo = 0;
 //BA.debugLineNum = 1834;BA.debugLine="Dim aux_tiempo As Int";
_aux_tiempo = 0;
 //BA.debugLineNum = 1835;BA.debugLine="Dim aux_tipo As String";
_aux_tipo = "";
 //BA.debugLineNum = 1836;BA.debugLine="numerolineas = lvDatos.Size";
_numerolineas = mostCurrent._lvdatos.getSize();
 //BA.debugLineNum = 1837;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 1838;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 1839;BA.debugLine="Dim num As Int";
_num = 0;
 //BA.debugLineNum = 1840;BA.debugLine="Dim servo_actual_nombre As String";
_servo_actual_nombre = "";
 //BA.debugLineNum = 1841;BA.debugLine="num =numerolineas-5";
_num = (int) (_numerolineas-5);
 //BA.debugLineNum = 1843;BA.debugLine="If servo_actual=1 Then";
if (_servo_actual==1) { 
 //BA.debugLineNum = 1844;BA.debugLine="servo_actual_nombre=\"INC.STABILO 1\"";
_servo_actual_nombre = "INC.STABILO 1";
 };
 //BA.debugLineNum = 1846;BA.debugLine="If servo_actual=2 Then";
if (_servo_actual==2) { 
 //BA.debugLineNum = 1847;BA.debugLine="servo_actual_nombre=\"DIRECCION   2\"";
_servo_actual_nombre = "DIRECCION   2";
 };
 //BA.debugLineNum = 1849;BA.debugLine="If servo_actual=3 Then";
if (_servo_actual==3) { 
 //BA.debugLineNum = 1850;BA.debugLine="servo_actual_nombre=\"C.INCI.ALAS 3\"";
_servo_actual_nombre = "C.INCI.ALAS 3";
 };
 //BA.debugLineNum = 1852;BA.debugLine="If servo_actual=4 Then";
if (_servo_actual==4) { 
 //BA.debugLineNum = 1853;BA.debugLine="servo_actual_nombre=\"FLAPPER     4\"";
_servo_actual_nombre = "FLAPPER     4";
 };
 //BA.debugLineNum = 1855;BA.debugLine="If servo_actual=5 Then";
if (_servo_actual==5) { 
 //BA.debugLineNum = 1856;BA.debugLine="servo_actual_nombre=\"GANCHO/FLAP 5\"";
_servo_actual_nombre = "GANCHO/FLAP 5";
 };
 //BA.debugLineNum = 1861;BA.debugLine="Log(\"Servo \"& servo_actual &\"tiempo Actual \"&tiemp";
anywheresoftware.b4a.keywords.Common.Log("Servo "+BA.NumberToString(_servo_actual)+"tiempo Actual "+BA.NumberToString(_tiempo_actual1)+" Numero_lineas "+BA.NumberToString(_numerolineas)+"numero lineas-5 "+BA.NumberToString(_num));
 //BA.debugLineNum = 1862;BA.debugLine="For  i= num To  numerolineas-1";
{
final int step1277 = 1;
final int limit1277 = (int) (_numerolineas-1);
for (_i = _num; (step1277 > 0 && _i <= limit1277) || (step1277 < 0 && _i >= limit1277); _i = ((int)(0 + _i + step1277))) {
 //BA.debugLineNum = 1864;BA.debugLine="If i<20 Then";
if (_i<20) { 
 }else {
 //BA.debugLineNum = 1869;BA.debugLine="str =lvDatos.GetItem(lvDatos.Size-1)";
_str = BA.ObjectToString(mostCurrent._lvdatos.GetItem((int) (mostCurrent._lvdatos.getSize()-1)));
 //BA.debugLineNum = 1870;BA.debugLine="aux_servo = str.SubString2(12,13)";
_aux_servo = (int)(Double.parseDouble(_str.substring((int) (12),(int) (13))));
 //BA.debugLineNum = 1871;BA.debugLine="aux_tiempo=str.SubString2(36,40)";
_aux_tiempo = (int)(Double.parseDouble(_str.substring((int) (36),(int) (40))));
 //BA.debugLineNum = 1872;BA.debugLine="aux_tipo=str.SubString2(55,56)";
_aux_tipo = _str.substring((int) (55),(int) (56));
 //BA.debugLineNum = 1873;BA.debugLine="Log(\"cadena \"&str)";
anywheresoftware.b4a.keywords.Common.Log("cadena "+_str);
 //BA.debugLineNum = 1875;BA.debugLine="Log(\"aux_servo \"&aux_servo)";
anywheresoftware.b4a.keywords.Common.Log("aux_servo "+BA.NumberToString(_aux_servo));
 //BA.debugLineNum = 1876;BA.debugLine="Log(\"aux_tiempo \"&aux_tiempo)";
anywheresoftware.b4a.keywords.Common.Log("aux_tiempo "+BA.NumberToString(_aux_tiempo));
 //BA.debugLineNum = 1877;BA.debugLine="Log(\"aux_tipo \"&aux_tipo)";
anywheresoftware.b4a.keywords.Common.Log("aux_tipo "+_aux_tipo);
 //BA.debugLineNum = 1881;BA.debugLine="If aux_tipo=\"D\" Then";
if ((_aux_tipo).equals("D")) { 
 //BA.debugLineNum = 1882;BA.debugLine="aux_tiempo=aux_tiempo";
_aux_tiempo = _aux_tiempo;
 //BA.debugLineNum = 1883;BA.debugLine="Log(\"Tiempo Actual decimas convertir decimas: \"&au";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual decimas convertir decimas: "+BA.NumberToString(_aux_tiempo));
 };
 //BA.debugLineNum = 1886;BA.debugLine="If aux_tipo=\"S\" Then";
if ((_aux_tipo).equals("S")) { 
 //BA.debugLineNum = 1887;BA.debugLine="aux_tiempo=aux_tiempo*10";
_aux_tiempo = (int) (_aux_tiempo*10);
 //BA.debugLineNum = 1888;BA.debugLine="Log(\"Tiempo Actual segundos convertir decimas: \"&a";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos convertir decimas: "+BA.NumberToString(_aux_tiempo));
 };
 //BA.debugLineNum = 1890;BA.debugLine="If aux_tipo=\"M\" Then";
if ((_aux_tipo).equals("M")) { 
 //BA.debugLineNum = 1891;BA.debugLine="aux_tiempo=aux_tiempo*600";
_aux_tiempo = (int) (_aux_tiempo*600);
 //BA.debugLineNum = 1892;BA.debugLine="Log(\"Tiempo Actual minutos convertir decimas: \"&au";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos convertir decimas: "+BA.NumberToString(_aux_tiempo));
 };
 //BA.debugLineNum = 1901;BA.debugLine="If aux_tiempo>=tiempo_actual1 Then";
if (_aux_tiempo>=_tiempo_actual1) { 
 //BA.debugLineNum = 1903;BA.debugLine="If aux_servo=servo_actual Then";
if (_aux_servo==_servo_actual) { 
 //BA.debugLineNum = 1905;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1907;BA.debugLine="Msgbox(\"Mismo TIEMPO y mismo SERVO\",\"ERROR\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Mismo TIEMPO y mismo SERVO","ERROR",mostCurrent.activityBA);
 //BA.debugLineNum = 1909;BA.debugLine="ex=False";
_ex = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1910;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1911;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1912;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1913;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1914;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1915;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1916;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1917;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1918;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1919;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1920;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1921;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1922;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1923;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 1926;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1927;BA.debugLine="tiempo=tiempo_actual1";
mostCurrent._tiempo = BA.NumberToString(_tiempo_actual1);
 //BA.debugLineNum = 1928;BA.debugLine="Log(\"Tiempo final : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo final : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1931;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1932;BA.debugLine="tiempo=tiempo_actual1/10";
mostCurrent._tiempo = BA.NumberToString(_tiempo_actual1/(double)10);
 //BA.debugLineNum = 1933;BA.debugLine="Log(\"Tiempo final : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo final : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1935;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1936;BA.debugLine="tiempo=tiempo_actual1/600";
mostCurrent._tiempo = BA.NumberToString(_tiempo_actual1/(double)600);
 //BA.debugLineNum = 1937;BA.debugLine="Log(\"Tiempo final : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo final : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1942;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1943;BA.debugLine="ex=True";
_ex = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1944;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 };
 }
};
 //BA.debugLineNum = 1960;BA.debugLine="End Sub";
return false;
}
public static String  _mod_datos(int _valor_ini,int _valor_fin,int _valor_sum) throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
 //BA.debugLineNum = 3157;BA.debugLine="Sub mod_datos (valor_ini As Int, valor_fin As Int,";
 //BA.debugLineNum = 3158;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 3159;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 3160;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 3161;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 3162;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 3165;BA.debugLine="If posicion_mod<valor_fin Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<_valor_fin) { 
 //BA.debugLineNum = 3166;BA.debugLine="If posicion_mod>=valor_ini Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=_valor_ini) { 
 //BA.debugLineNum = 3168;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 3169;BA.debugLine="tiempo=tiempo/10";
mostCurrent._tiempo = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo))/(double)10);
 //BA.debugLineNum = 3170;BA.debugLine="Log(\"Tiempo Actual segundos : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 3172;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 3173;BA.debugLine="tiempo=tiempo/600";
mostCurrent._tiempo = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo))/(double)600);
 //BA.debugLineNum = 3174;BA.debugLine="Log(\"Tiempo Actual minutos: \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos: "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 3176;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 3177;BA.debugLine="pos_valor=(pos_mod_aux*4)+valor_sum";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+_valor_sum);
 //BA.debugLineNum = 3180;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 3182;BA.debugLine="aux_valor_tiempo=\"00\"&tiempo";
_aux_valor_tiempo = "00"+mostCurrent._tiempo;
 //BA.debugLineNum = 3183;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 3186;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 3187;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 3188;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 3190;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 3194;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 3197;BA.debugLine="lista1.Set(pos_valor,tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(mostCurrent._tiempo));
 };
 //BA.debugLineNum = 3201;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 3202;BA.debugLine="lista1.set(pos_valor+1,\"0\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("0"));
 };
 //BA.debugLineNum = 3204;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 3205;BA.debugLine="lista1.set(pos_valor+1,\"1\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("1"));
 };
 //BA.debugLineNum = 3207;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 3208;BA.debugLine="lista1.set(pos_valor+1,\"2\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("2"));
 };
 //BA.debugLineNum = 3211;BA.debugLine="lista1.set(pos_valor+2,servo)";
mostCurrent._lista1.Set((int) (_pos_valor+2),(Object)(mostCurrent._servo));
 //BA.debugLineNum = 3214;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 3215;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 3216;BA.debugLine="lista1.set(pos_valor+3,aux_valor_giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 3219;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 3220;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 3221;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 3222;BA.debugLine="lista1.set(pos_valor+3,aux_valor_giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 3226;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 3227;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 };
 };
 //BA.debugLineNum = 3236;BA.debugLine="End Sub";
return "";
}
public static String  _mod_datos_button2() throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
int _i = 0;
 //BA.debugLineNum = 1561;BA.debugLine="Sub mod_datos_button2";
 //BA.debugLineNum = 1562;BA.debugLine="Try";
try { //BA.debugLineNum = 1563;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 1564;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 1565;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 1566;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 1567;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 1568;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1569;BA.debugLine="tiempo=tiempo";
mostCurrent._tiempo = mostCurrent._tiempo;
 //BA.debugLineNum = 1570;BA.debugLine="Log(\"Tiempo Actual decimas: \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual decimas: "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1583;BA.debugLine="If posicion_mod<30 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<30) { 
 //BA.debugLineNum = 1584;BA.debugLine="If posicion_mod>=20 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=20) { 
 //BA.debugLineNum = 1586;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1587;BA.debugLine="tiempo=tiempo/10";
mostCurrent._tiempo = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo))/(double)10);
 //BA.debugLineNum = 1588;BA.debugLine="Log(\"Tiempo Actual segundos : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1590;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1591;BA.debugLine="tiempo=tiempo/600";
mostCurrent._tiempo = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo))/(double)600);
 //BA.debugLineNum = 1592;BA.debugLine="Log(\"Tiempo Actual minutos: \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos: "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1595;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1596;BA.debugLine="pos_valor=(pos_mod_aux*4)+20";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+20);
 //BA.debugLineNum = 1599;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 1601;BA.debugLine="aux_valor_tiempo=\"\"";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 1602;BA.debugLine="aux_valor_tiempo=\"00\"&tiempo";
_aux_valor_tiempo = "00"+mostCurrent._tiempo;
 //BA.debugLineNum = 1603;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 1606;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 1607;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 1609;BA.debugLine="aux_valor_tiempo=\"\"";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 1610;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 1611;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 1615;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 1616;BA.debugLine="aux_valor_tiempo=\"\"";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 1617;BA.debugLine="aux_valor_tiempo=tiempo";
_aux_valor_tiempo = mostCurrent._tiempo;
 //BA.debugLineNum = 1619;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 1623;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1624;BA.debugLine="lista1.set(pos_valor+1,\"0\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("0"));
 };
 //BA.debugLineNum = 1626;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1627;BA.debugLine="lista1.set(pos_valor+1,\"1\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("1"));
 };
 //BA.debugLineNum = 1629;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1630;BA.debugLine="lista1.set(pos_valor+1,\"2\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("2"));
 };
 //BA.debugLineNum = 1633;BA.debugLine="lista1.set(pos_valor+2,servo)";
mostCurrent._lista1.Set((int) (_pos_valor+2),(Object)(mostCurrent._servo));
 //BA.debugLineNum = 1636;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 1638;BA.debugLine="giro=\"00\"&giro";
mostCurrent._giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 1640;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 //BA.debugLineNum = 1643;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 1644;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 1646;BA.debugLine="giro=\"0\"&giro";
mostCurrent._giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 1648;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 };
 //BA.debugLineNum = 1652;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 1653;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 };
 };
 //BA.debugLineNum = 1659;BA.debugLine="mod_datos(30,40,60)";
_mod_datos((int) (30),(int) (40),(int) (60));
 //BA.debugLineNum = 1661;BA.debugLine="mod_datos(40,50,100)";
_mod_datos((int) (40),(int) (50),(int) (100));
 //BA.debugLineNum = 1663;BA.debugLine="mod_datos(50,60,140)";
_mod_datos((int) (50),(int) (60),(int) (140));
 //BA.debugLineNum = 1665;BA.debugLine="mod_datos(60,70,180)";
_mod_datos((int) (60),(int) (70),(int) (180));
 //BA.debugLineNum = 1673;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 1674;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 1704;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1705;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (0)))+"  REMOLQUE");
 //BA.debugLineNum = 1706;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1707;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (1)))+"  REMOLQUE");
 //BA.debugLineNum = 1708;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1709;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (2)))+"  REMOLQUE");
 //BA.debugLineNum = 1710;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1711;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (3)))+"  REMOLQUE");
 //BA.debugLineNum = 1712;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1713;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (4)))+"  REMOLQUE");
 //BA.debugLineNum = 1715;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1716;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (5)))+"  RDT");
 //BA.debugLineNum = 1717;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1718;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (6)))+"  RDT");
 //BA.debugLineNum = 1719;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1720;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (7)))+"  RDT");
 //BA.debugLineNum = 1721;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1722;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (8)))+"  RDT");
 //BA.debugLineNum = 1723;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1724;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (9)))+"  RDT");
 //BA.debugLineNum = 1726;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1727;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (10)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1728;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1729;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (11)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1730;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1731;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (12)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1732;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1733;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (13)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1734;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1735;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (14)))+"  REMOLQUE CIRCULAR");
 //BA.debugLineNum = 1737;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1738;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (15)))+"  PRE-START");
 //BA.debugLineNum = 1739;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1740;BA.debugLine="lvDatos.AddSingleLine(\"DIRECCION   2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("DIRECCION   2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (16)))+"  PRE-START");
 //BA.debugLineNum = 1741;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1742;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (17)))+"  PRE-START");
 //BA.debugLineNum = 1743;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1744;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (18)))+"  PRE-START");
 //BA.debugLineNum = 1745;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1746;BA.debugLine="lvDatos.AddSingleLine(\"GANCHO/FLAP 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("GANCHO/FLAP 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (19)))+"  PRE-START");
 //BA.debugLineNum = 1759;BA.debugLine="For i=20 To lista1.Size-1 Step 4";
{
final int step1197 = (int) (4);
final int limit1197 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (20); (step1197 > 0 && _i <= limit1197) || (step1197 < 0 && _i >= limit1197); _i = ((int)(0 + _i + step1197))) {
 //BA.debugLineNum = 1760;BA.debugLine="tiempo_aux=lista1.Get(i)";
mostCurrent._tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get(_i));
 //BA.debugLineNum = 1761;BA.debugLine="tipo_tiempo_aux=lista1.Get(i+1)";
mostCurrent._tipo_tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+1)));
 //BA.debugLineNum = 1762;BA.debugLine="servo_aux=lista1.Get(i+2)";
mostCurrent._servo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+2)));
 //BA.debugLineNum = 1765;BA.debugLine="If servo_aux=1 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1766;BA.debugLine="servo_aux_nombre=\"INC.STABILO 1\"";
mostCurrent._servo_aux_nombre = "INC.STABILO 1";
 };
 //BA.debugLineNum = 1768;BA.debugLine="If servo_aux=2 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1769;BA.debugLine="servo_aux_nombre=\"DIRECCION   2\"";
mostCurrent._servo_aux_nombre = "DIRECCION   2";
 };
 //BA.debugLineNum = 1771;BA.debugLine="If servo_aux=3 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 1772;BA.debugLine="servo_aux_nombre=\"C.INCI.ALAS 3\"";
mostCurrent._servo_aux_nombre = "C.INCI.ALAS 3";
 };
 //BA.debugLineNum = 1774;BA.debugLine="If servo_aux=4 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 1775;BA.debugLine="servo_aux_nombre=\"FLAPPER     4\"";
mostCurrent._servo_aux_nombre = "FLAPPER     4";
 };
 //BA.debugLineNum = 1777;BA.debugLine="If servo_aux=5 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 1778;BA.debugLine="servo_aux_nombre=\"GANCHO/FLAP 5\"";
mostCurrent._servo_aux_nombre = "GANCHO/FLAP 5";
 };
 //BA.debugLineNum = 1782;BA.debugLine="giro_aux=lista1.Get(i+3)";
mostCurrent._giro_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+3)));
 //BA.debugLineNum = 1783;BA.debugLine="If tipo_tiempo_aux=0 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1784;BA.debugLine="tipo_tiempo_aux=\"DECIMAS\"";
mostCurrent._tipo_tiempo_aux = "DECIMAS";
 };
 //BA.debugLineNum = 1787;BA.debugLine="If tipo_tiempo_aux=1 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1788;BA.debugLine="tipo_tiempo_aux=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo_aux = "SEGUNDOS";
 };
 //BA.debugLineNum = 1791;BA.debugLine="If tipo_tiempo_aux=2 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1792;BA.debugLine="tipo_tiempo_aux=\"MINUTOS\"";
mostCurrent._tipo_tiempo_aux = "MINUTOS";
 };
 //BA.debugLineNum = 1794;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1796;BA.debugLine="lvDatos.AddSingleLine(servo_aux_nombre&\"   Gir";
mostCurrent._lvdatos.AddSingleLine(mostCurrent._servo_aux_nombre+"   Giro: "+mostCurrent._giro_aux+"    Tiempo: "+mostCurrent._tiempo_aux+"  Tipo tiempo: "+mostCurrent._tipo_tiempo_aux);
 }
};
 //BA.debugLineNum = 1800;BA.debugLine="tiempo_minimo=tiempo_aux";
_tiempo_minimo = (int)(Double.parseDouble(mostCurrent._tiempo_aux));
 //BA.debugLineNum = 1801;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1802;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1803;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1804;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1807;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1808;BA.debugLine="lbservotxt.Text=\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1809;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1810;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1811;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1812;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1813;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1814;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 1815;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1816;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1820;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1821;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1822;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e1248) {
			processBA.setLastException(e1248); };
 //BA.debugLineNum = 1829;BA.debugLine="End Sub";
return "";
}
public static String  _mod_tiempo(int _pos) throws Exception{
int _pos_valor = 0;
String _pos_mod_aux = "";
int _size = 0;
 //BA.debugLineNum = 856;BA.debugLine="Sub mod_tiempo(pos As Int)";
 //BA.debugLineNum = 862;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 863;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 864;BA.debugLine="Dim size As Int";
_size = 0;
 //BA.debugLineNum = 865;BA.debugLine="size = lvDatos.size-1";
_size = (int) (mostCurrent._lvdatos.getSize()-1);
 //BA.debugLineNum = 866;BA.debugLine="If pos=20 Then";
if (_pos==20) { 
 //BA.debugLineNum = 867;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 868;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 869;BA.debugLine="pos_valor=(pos_mod_aux*4)+20";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+20);
 }else {
 //BA.debugLineNum = 877;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 878;BA.debugLine="pos_valor=(pos_mod_aux*4)+20";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+20);
 //BA.debugLineNum = 879;BA.debugLine="tiempo_ante_lv=0";
mostCurrent._tiempo_ante_lv = BA.NumberToString(0);
 //BA.debugLineNum = 880;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 881;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 882;BA.debugLine="servo_ante_lv=10";
mostCurrent._servo_ante_lv = BA.NumberToString(10);
 //BA.debugLineNum = 883;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 //BA.debugLineNum = 888;BA.debugLine="If pos<40 Then";
if (_pos<40) { 
 //BA.debugLineNum = 889;BA.debugLine="If pos>20 Then";
if (_pos>20) { 
 //BA.debugLineNum = 890;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 891;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 892;BA.debugLine="pos_valor=(pos_mod_aux*4)+20";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+20);
 //BA.debugLineNum = 893;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 894;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 895;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 896;BA.debugLine="tiempo_post_lv=9999999999999999999";
mostCurrent._tiempo_post_lv = BA.NumberToString(9999999999999999999d);
 //BA.debugLineNum = 897;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 //BA.debugLineNum = 899;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 900;BA.debugLine="pos_valor=(pos_mod_aux*4)+20";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+20);
 //BA.debugLineNum = 901;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 902;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 903;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 904;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 905;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 };
 //BA.debugLineNum = 914;BA.debugLine="If pos<40 Then";
if (_pos<40) { 
 //BA.debugLineNum = 915;BA.debugLine="If pos>=30 Then";
if (_pos>=30) { 
 //BA.debugLineNum = 916;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 917;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 918;BA.debugLine="pos_valor=(pos_mod_aux*4)+60";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+60);
 //BA.debugLineNum = 919;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 920;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 921;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 922;BA.debugLine="tiempo_post_lv=9999999999999999999";
mostCurrent._tiempo_post_lv = BA.NumberToString(9999999999999999999d);
 //BA.debugLineNum = 923;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 //BA.debugLineNum = 925;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 926;BA.debugLine="pos_valor=(pos_mod_aux*4)+60";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+60);
 //BA.debugLineNum = 927;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 928;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 929;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 930;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 931;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 };
 //BA.debugLineNum = 942;BA.debugLine="If pos<50 Then";
if (_pos<50) { 
 //BA.debugLineNum = 943;BA.debugLine="If pos>=40 Then";
if (_pos>=40) { 
 //BA.debugLineNum = 944;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 945;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 946;BA.debugLine="pos_valor=(pos_mod_aux*4)+100";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+100);
 //BA.debugLineNum = 947;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 948;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 949;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 950;BA.debugLine="tiempo_post_lv=9999999999999999999";
mostCurrent._tiempo_post_lv = BA.NumberToString(9999999999999999999d);
 //BA.debugLineNum = 951;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 //BA.debugLineNum = 953;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 954;BA.debugLine="pos_valor=(pos_mod_aux*4)+100";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+100);
 //BA.debugLineNum = 955;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 956;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 957;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 958;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 959;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 };
 //BA.debugLineNum = 972;BA.debugLine="If pos<60 Then";
if (_pos<60) { 
 //BA.debugLineNum = 973;BA.debugLine="If pos>=50 Then";
if (_pos>=50) { 
 //BA.debugLineNum = 974;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 975;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 976;BA.debugLine="pos_valor=(pos_mod_aux*4)+140";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+140);
 //BA.debugLineNum = 977;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 978;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 979;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 980;BA.debugLine="tiempo_post_lv=9999999999999999999";
mostCurrent._tiempo_post_lv = BA.NumberToString(9999999999999999999d);
 //BA.debugLineNum = 981;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 //BA.debugLineNum = 983;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 984;BA.debugLine="pos_valor=(pos_mod_aux*4)+140";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+140);
 //BA.debugLineNum = 985;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 986;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 987;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 988;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 989;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 };
 //BA.debugLineNum = 998;BA.debugLine="If pos<70 Then";
if (_pos<70) { 
 //BA.debugLineNum = 999;BA.debugLine="If pos>=60 Then";
if (_pos>=60) { 
 //BA.debugLineNum = 1000;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 1001;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1002;BA.debugLine="pos_valor=(pos_mod_aux*4)+180";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+180);
 //BA.debugLineNum = 1003;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1004;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 1005;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1006;BA.debugLine="tiempo_post_lv=9999999999999999999";
mostCurrent._tiempo_post_lv = BA.NumberToString(9999999999999999999d);
 //BA.debugLineNum = 1007;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 };
 };
 };
 //BA.debugLineNum = 1021;BA.debugLine="End Sub";
return "";
}
public static String  _nada() throws Exception{
 //BA.debugLineNum = 2047;BA.debugLine="Sub nada";
 //BA.debugLineNum = 2223;BA.debugLine="End Sub";
return "";
}
public static String  _openusb() throws Exception{
int _dev = 0;
 //BA.debugLineNum = 3320;BA.debugLine="Sub openusb";
 //BA.debugLineNum = 3321;BA.debugLine="If usb1.UsbPresent(1)=usb1.USB_NONE Then";
if (_usb1.UsbPresent((int) (1))==_usb1.USB_NONE) { 
 //BA.debugLineNum = 3323;BA.debugLine="Log(\"Msgbox - no device\")";
anywheresoftware.b4a.keywords.Common.Log("Msgbox - no device");
 //BA.debugLineNum = 3324;BA.debugLine="Msgbox(\"No USB device or accessory detected!\", \"";
anywheresoftware.b4a.keywords.Common.Msgbox("No USB device or accessory detected!","Error",mostCurrent.activityBA);
 //BA.debugLineNum = 3325;BA.debugLine="Log(\"Msgbox - returned\")";
anywheresoftware.b4a.keywords.Common.Log("Msgbox - returned");
 };
 //BA.debugLineNum = 3327;BA.debugLine="Log(\"Checking permission 1\")";
anywheresoftware.b4a.keywords.Common.Log("Checking permission 1");
 //BA.debugLineNum = 3328;BA.debugLine="If (usb1.HasPermission(1)) Then	' Ver_2.4";
if ((_usb1.HasPermission((int) (1)))) { 
 //BA.debugLineNum = 3330;BA.debugLine="Dim dev As Int";
_dev = 0;
 //BA.debugLineNum = 3332;BA.debugLine="dev = usb1.Open(9600,1)		' Ver_2.4";
_dev = _usb1.Open(processBA,(int) (9600),(int) (1));
 //BA.debugLineNum = 3333;BA.debugLine="If dev <> usb1.USB_NONE Then";
if (_dev!=_usb1.USB_NONE) { 
 //BA.debugLineNum = 3334;BA.debugLine="Log(\"Connected successfully! 1\")";
anywheresoftware.b4a.keywords.Common.Log("Connected successfully! 1");
 //BA.debugLineNum = 3335;BA.debugLine="astreams1.Initialize(usb1.GetInputStream, usb";
_astreams1.Initialize(processBA,_usb1.GetInputStream(),_usb1.GetOutputStream(),"astreams1");
 //BA.debugLineNum = 3336;BA.debugLine="flag_inicio_usb=True";
_flag_inicio_usb = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 3338;BA.debugLine="Log(\"Error opening USB port 1\")";
anywheresoftware.b4a.keywords.Common.Log("Error opening USB port 1");
 };
 }else {
 //BA.debugLineNum = 3341;BA.debugLine="usb1.requestPermission(1)  ' Ver_2.4";
_usb1.RequestPermission((int) (1));
 };
 //BA.debugLineNum = 3343;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim flag As Int=0";
_flag = (int) (0);
 //BA.debugLineNum = 11;BA.debugLine="Dim strModelo As String";
_strmodelo = "";
 //BA.debugLineNum = 12;BA.debugLine="Dim usb1 As UsbSerial";
_usb1 = new anywheresoftware.b4a.objects.UsbSerial();
 //BA.debugLineNum = 13;BA.debugLine="Dim strspinner As String";
_strspinner = "";
 //BA.debugLineNum = 14;BA.debugLine="Dim strfichero As String";
_strfichero = "";
 //BA.debugLineNum = 15;BA.debugLine="Dim astreams1 As AsyncStreams";
_astreams1 = new anywheresoftware.b4a.randomaccessfile.AsyncStreams();
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static String  _send_data(String _data) throws Exception{
byte[] _buffer = null;
 //BA.debugLineNum = 3521;BA.debugLine="Sub send_data(data As String)";
 //BA.debugLineNum = 3523;BA.debugLine="Dim buffer() As Byte";
_buffer = new byte[(int) (0)];
;
 //BA.debugLineNum = 3525;BA.debugLine="buffer = data.GetBytes(\"UTF8\")";
_buffer = _data.getBytes("UTF8");
 //BA.debugLineNum = 3526;BA.debugLine="Log(\"data \"&data)";
anywheresoftware.b4a.keywords.Common.Log("data "+_data);
 //BA.debugLineNum = 3527;BA.debugLine="Log(\"Buffer \"&buffer(0))";
anywheresoftware.b4a.keywords.Common.Log("Buffer "+BA.NumberToString(_buffer[(int) (0)]));
 //BA.debugLineNum = 3528;BA.debugLine="t=t+1";
_t = (int) (_t+1);
 //BA.debugLineNum = 3529;BA.debugLine="Log(\"contador: \"&t)";
anywheresoftware.b4a.keywords.Common.Log("contador: "+BA.NumberToString(_t));
 //BA.debugLineNum = 3530;BA.debugLine="astreams1.Write(buffer)";
_astreams1.Write(_buffer);
 //BA.debugLineNum = 3531;BA.debugLine="End Sub";
return "";
}
public static String  _spconfig_itemclick(int _position,Object _value) throws Exception{
int _numpos = 0;
 //BA.debugLineNum = 315;BA.debugLine="Sub spconfig_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 317;BA.debugLine="Dim numpos As Int";
_numpos = 0;
 //BA.debugLineNum = 318;BA.debugLine="Try";
try { //BA.debugLineNum = 320;BA.debugLine="If Value=\"PULSE AQUI\" Then";
if ((_value).equals((Object)("PULSE AQUI"))) { 
 //BA.debugLineNum = 322;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 325;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 326;BA.debugLine="config=\" \"&Value";
mostCurrent._config = " "+BA.ObjectToString(_value);
 //BA.debugLineNum = 328;BA.debugLine="NOMBRE_FICHERO=strfichero&config";
mostCurrent._nombre_fichero = _strfichero+mostCurrent._config;
 //BA.debugLineNum = 329;BA.debugLine="etFichero.Text=NOMBRE_FICHERO";
mostCurrent._etfichero.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 330;BA.debugLine="spconfig.Enabled=False";
mostCurrent._spconfig.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 332;BA.debugLine="lista1.Clear";
mostCurrent._lista1.Clear();
 //BA.debugLineNum = 335;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 336;BA.debugLine="strModelo=\"Modelo_F1A_\"&NOMBRE_FICHERO";
_strmodelo = "Modelo_F1A_"+mostCurrent._nombre_fichero;
 //BA.debugLineNum = 338;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 339;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 340;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 341;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 342;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 343;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 344;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 345;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 346;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 347;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 348;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 349;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 350;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 351;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 352;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 353;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 //BA.debugLineNum = 354;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 355;BA.debugLine="btOK.Enabled=True";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 356;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 357;BA.debugLine="lbNumeroServo.Text=\"STABILO\"";
mostCurrent._lbnumeroservo.setText((Object)("STABILO"));
 //BA.debugLineNum = 358;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 //BA.debugLineNum = 359;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 360;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 361;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 362;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 363;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 364;BA.debugLine="btOK.Visible=True";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 366;BA.debugLine="tiempo_actual=0";
_tiempo_actual = (int) (0);
 //BA.debugLineNum = 367;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 368;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 };
 //BA.debugLineNum = 371;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 } 
       catch (Exception e268) {
			processBA.setLastException(e268); //BA.debugLineNum = 373;BA.debugLine="NOMBRE_FICHERO=etFichero.Text";
mostCurrent._nombre_fichero = mostCurrent._etfichero.getText();
 //BA.debugLineNum = 375;BA.debugLine="If File.Exists(File.DirRootExternal & \"/Consola/";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 376;BA.debugLine="lista1.Clear";
mostCurrent._lista1.Clear();
 //BA.debugLineNum = 379;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 380;BA.debugLine="strModelo=\"Modelo_F1A_\"&NOMBRE_FICHERO";
_strmodelo = "Modelo_F1A_"+mostCurrent._nombre_fichero;
 //BA.debugLineNum = 381;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 382;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 383;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 384;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 385;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 386;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 387;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 388;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 389;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 390;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 391;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 392;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 393;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 394;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 395;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 396;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 //BA.debugLineNum = 397;BA.debugLine="lbNumeroServo.Text=\"STABILO\"";
mostCurrent._lbnumeroservo.setText((Object)("STABILO"));
 //BA.debugLineNum = 398;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 //BA.debugLineNum = 399;BA.debugLine="btOK.Enabled=True";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 400;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 401;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 402;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 403;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 404;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 405;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 406;BA.debugLine="btOK.Visible=True";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 408;BA.debugLine="tiempo_actual=0";
_tiempo_actual = (int) (0);
 //BA.debugLineNum = 409;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 410;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 };
 //BA.debugLineNum = 413;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 };
 //BA.debugLineNum = 417;BA.debugLine="listaconfiguracion.Set(1,NOMBRE_FICHERO)";
mostCurrent._listaconfiguracion.Set((int) (1),(Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 419;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consol";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1A/configuracion","Modelo_F1A_"+mostCurrent._nombre_fichero,mostCurrent._listaconfiguracion);
 //BA.debugLineNum = 424;BA.debugLine="End Sub";
return "";
}
public static String  _spservo_itemclick(int _position,Object _value) throws Exception{
String _nom_ser = "";
 //BA.debugLineNum = 284;BA.debugLine="Sub spServo_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 285;BA.debugLine="Dim nom_ser As String";
_nom_ser = "";
 //BA.debugLineNum = 287;BA.debugLine="If Position=0 Then";
if (_position==0) { 
 }else {
 //BA.debugLineNum = 291;BA.debugLine="servo=Position";
mostCurrent._servo = BA.NumberToString(_position);
 //BA.debugLineNum = 292;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 293;BA.debugLine="nom_ser=\"STABILO\"";
_nom_ser = "STABILO";
 };
 //BA.debugLineNum = 295;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 296;BA.debugLine="nom_ser=\"TIMON DIREC\"";
_nom_ser = "TIMON DIREC";
 };
 //BA.debugLineNum = 298;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 299;BA.debugLine="nom_ser=\"C.I.ALA\"";
_nom_ser = "C.I.ALA";
 };
 //BA.debugLineNum = 301;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 302;BA.debugLine="nom_ser=\"FLAP-FOLDER\"";
_nom_ser = "FLAP-FOLDER";
 };
 //BA.debugLineNum = 304;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 305;BA.debugLine="nom_ser=\"GANCHO/FLAP\"";
_nom_ser = "GANCHO/FLAP";
 };
 //BA.debugLineNum = 307;BA.debugLine="lbNumeroServo.Text=nom_ser";
mostCurrent._lbnumeroservo.setText((Object)(_nom_ser));
 //BA.debugLineNum = 308;BA.debugLine="lbservotxt.Text=nom_ser";
mostCurrent._lbservotxt.setText((Object)(_nom_ser));
 //BA.debugLineNum = 309;BA.debugLine="spTiempo.Enabled=True";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 312;BA.debugLine="End Sub";
return "";
}
public static String  _sptiempo_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 2733;BA.debugLine="Sub spTiempo_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 2735;BA.debugLine="If Value=\"PULSE AQUI\" Then";
if ((_value).equals((Object)("PULSE AQUI"))) { 
 //BA.debugLineNum = 2736;BA.debugLine="ToastMessageShow(\"VALOR NO VALIDO\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("VALOR NO VALIDO",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 2740;BA.debugLine="tipo_tiempo=Value";
mostCurrent._tipo_tiempo = BA.ObjectToString(_value);
 //BA.debugLineNum = 2741;BA.debugLine="etTiempo.Enabled=True";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 2748;BA.debugLine="End Sub";
return "";
}
public static String  _tim_tick() throws Exception{
 //BA.debugLineNum = 250;BA.debugLine="Sub tim_Tick";
 //BA.debugLineNum = 251;BA.debugLine="tim.Enabled=False";
mostCurrent._tim.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 252;BA.debugLine="lbModelo_LongClick";
_lbmodelo_longclick();
 //BA.debugLineNum = 254;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 3388;BA.debugLine="Sub Timer1_tick";
 //BA.debugLineNum = 3389;BA.debugLine="If usb1.UsbPresent(1)=usb1.USB_NONE Then";
if (_usb1.UsbPresent((int) (1))==_usb1.USB_NONE) { 
 //BA.debugLineNum = 3391;BA.debugLine="btgrabar.Enabled=False";
mostCurrent._btgrabar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 3392;BA.debugLine="btLeer.Enabled=False";
mostCurrent._btleer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 3394;BA.debugLine="btgrabar.Enabled=True";
mostCurrent._btgrabar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 3395;BA.debugLine="btLeer.Enabled=True";
mostCurrent._btleer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 3400;BA.debugLine="End Sub";
return "";
}
}
