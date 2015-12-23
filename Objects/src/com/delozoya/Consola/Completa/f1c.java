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

public class f1c extends Activity implements B4AActivity{
	public static f1c mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.delozoya.Consola.Completa", "com.delozoya.Consola.Completa.f1c");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (f1c).");
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
		activityBA = new BA(this, layout, processBA, "com.delozoya.Consola.Completa", "com.delozoya.Consola.Completa.f1c");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.delozoya.Consola.Completa.f1c", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (f1c) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (f1c) Resume **");
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
		return f1c.class;
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
        BA.LogInfo("** Activity (f1c) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (f1c) Resume **");
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
public static anywheresoftware.b4a.randomaccessfile.AsyncStreams _astreams1 = null;
public static int _flag = 0;
public static String _strmodelo = "";
public static anywheresoftware.b4a.objects.UsbSerial _usb1 = null;
public static String _strspinner = "";
public static String _strfichero = "";
public com.delozoya.Consola.Completa.clsexplorer _dlgfileexpl = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btok = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etfichero = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etgiro = null;
public anywheresoftware.b4a.objects.EditTextWrapper _ettiempo = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvdatos = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spconfig = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spservo = null;
public anywheresoftware.b4a.objects.Timer _timer1 = null;
public static boolean _ex = false;
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
public static String _directorio = "";
public static String _tiempo_aux = "";
public static String _tipo_tiempo_aux = "";
public static String _tipo_tiempo_aux1 = "";
public static String _giro_aux = "";
public static String _servo_aux = "";
public static String _servo_aux_nombre = "";
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
public anywheresoftware.b4a.objects.ButtonWrapper _btgrabar = null;
public static int _tiempo_minimo = 0;
public static int _tiempo_actual = 0;
public static boolean _tiempo_repite = false;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.Timer _tim = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbservotxt = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbled = null;
public static String _nombre_leido = "";
public static boolean _flag_lectura = false;
public static int _t = 0;
public com.delozoya.Consola.Completa.main _main = null;
public com.delozoya.Consola.Completa.f1a _f1a = null;
public com.delozoya.Consola.Completa.f1b1 _f1b1 = null;
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
 //BA.debugLineNum = 2645;BA.debugLine="Sub Abrir_Fichero_leido (nombre As String)";
 //BA.debugLineNum = 2646;BA.debugLine="lbModelo.Text=nombre";
mostCurrent._lbmodelo.setText((Object)(_nombre));
 //BA.debugLineNum = 2647;BA.debugLine="strfichero=nombre";
_strfichero = _nombre;
 //BA.debugLineNum = 2648;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 2649;BA.debugLine="Dim aux As String";
_aux = "";
 //BA.debugLineNum = 2651;BA.debugLine="If nombre.Contains(\"PRUEBA\") Then";
if (_nombre.contains("PRUEBA")) { 
 //BA.debugLineNum = 2652;BA.debugLine="strspinner=\"PRUEBA\"";
_strspinner = "PRUEBA";
 };
 //BA.debugLineNum = 2654;BA.debugLine="If nombre.Contains(\"PRIMERA HORA\") Then";
if (_nombre.contains("PRIMERA HORA")) { 
 //BA.debugLineNum = 2655;BA.debugLine="strspinner=\"PRIMERA HORA\"";
_strspinner = "PRIMERA HORA";
 };
 //BA.debugLineNum = 2657;BA.debugLine="If nombre.Contains(\"VIENTO ALTO\") Then";
if (_nombre.contains("VIENTO ALTO")) { 
 //BA.debugLineNum = 2658;BA.debugLine="strspinner=\"VIENTO ALTO\"";
_strspinner = "VIENTO ALTO";
 };
 //BA.debugLineNum = 2660;BA.debugLine="If nombre.Contains(\"VIENTO MEDIO\") Then";
if (_nombre.contains("VIENTO MEDIO")) { 
 //BA.debugLineNum = 2661;BA.debugLine="strspinner=\"VIENTO MEDIO\"";
_strspinner = "VIENTO MEDIO";
 };
 //BA.debugLineNum = 2663;BA.debugLine="If nombre.Contains(\"CALMA\") Then";
if (_nombre.contains("CALMA")) { 
 //BA.debugLineNum = 2664;BA.debugLine="strspinner=\"CALMA\"";
_strspinner = "CALMA";
 };
 //BA.debugLineNum = 2666;BA.debugLine="If nombre.Contains(\"TERMICA\") Then";
if (_nombre.contains("TERMICA")) { 
 //BA.debugLineNum = 2667;BA.debugLine="strspinner=\"TERMICA\"";
_strspinner = "TERMICA";
 };
 //BA.debugLineNum = 2669;BA.debugLine="AbrirFichero";
_abrirfichero();
 //BA.debugLineNum = 2670;BA.debugLine="End Sub";
return "";
}
public static String  _abrirfichero() throws Exception{
int _numpos = 0;
int _i = 0;
 //BA.debugLineNum = 280;BA.debugLine="Sub AbrirFichero";
 //BA.debugLineNum = 281;BA.debugLine="Dim numpos As Int";
_numpos = 0;
 //BA.debugLineNum = 282;BA.debugLine="Try";
try { //BA.debugLineNum = 288;BA.debugLine="Log(\"abro fichero\")";
anywheresoftware.b4a.keywords.Common.Log("abro fichero");
 //BA.debugLineNum = 289;BA.debugLine="NOMBRE_FICHERO=strfichero";
mostCurrent._nombre_fichero = _strfichero;
 //BA.debugLineNum = 290;BA.debugLine="etFichero.Text=NOMBRE_FICHERO";
mostCurrent._etfichero.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 291;BA.debugLine="numpos=NOMBRE_FICHERO.IndexOf(\" \")";
_numpos = mostCurrent._nombre_fichero.indexOf(" ");
 //BA.debugLineNum = 292;BA.debugLine="ToastMessageShow(NOMBRE_FICHERO,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(mostCurrent._nombre_fichero,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 298;BA.debugLine="ToastMessageShow(numpos,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.NumberToString(_numpos),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 299;BA.debugLine="If strspinner=\"PRUEBA\" Then";
if ((_strspinner).equals("PRUEBA")) { 
 //BA.debugLineNum = 300;BA.debugLine="spconfig.SelectedIndex=1";
mostCurrent._spconfig.setSelectedIndex((int) (1));
 };
 //BA.debugLineNum = 302;BA.debugLine="If strspinner=\"PRIMERA HORA\" Then";
if ((_strspinner).equals("PRIMERA HORA")) { 
 //BA.debugLineNum = 303;BA.debugLine="spconfig.SelectedIndex=2";
mostCurrent._spconfig.setSelectedIndex((int) (2));
 };
 //BA.debugLineNum = 305;BA.debugLine="If strspinner=\"VIENTO ALTO\" Then";
if ((_strspinner).equals("VIENTO ALTO")) { 
 //BA.debugLineNum = 306;BA.debugLine="spconfig.SelectedIndex=3";
mostCurrent._spconfig.setSelectedIndex((int) (3));
 };
 //BA.debugLineNum = 308;BA.debugLine="If strspinner=\"VIENTO MEDIO\" Then";
if ((_strspinner).equals("VIENTO MEDIO")) { 
 //BA.debugLineNum = 309;BA.debugLine="spconfig.SelectedIndex=4";
mostCurrent._spconfig.setSelectedIndex((int) (4));
 };
 //BA.debugLineNum = 311;BA.debugLine="If strspinner=\"CALMA\" Then";
if ((_strspinner).equals("CALMA")) { 
 //BA.debugLineNum = 312;BA.debugLine="spconfig.SelectedIndex=5";
mostCurrent._spconfig.setSelectedIndex((int) (5));
 };
 //BA.debugLineNum = 314;BA.debugLine="If strspinner=\"TERMICA\" Then";
if ((_strspinner).equals("TERMICA")) { 
 //BA.debugLineNum = 315;BA.debugLine="spconfig.SelectedIndex=6";
mostCurrent._spconfig.setSelectedIndex((int) (6));
 };
 //BA.debugLineNum = 320;BA.debugLine="If File.Exists(File.DirRootExternal & \"/Consola/";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 323;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 324;BA.debugLine="strModelo=\"Modelo_F1C_\"&NOMBRE_FICHERO";
_strmodelo = "Modelo_F1C_"+mostCurrent._nombre_fichero;
 //BA.debugLineNum = 325;BA.debugLine="lista1=File.ReadList(File.DirRootExternal & \"/Con";
mostCurrent._lista1 = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero);
 //BA.debugLineNum = 326;BA.debugLine="listaconfiguracion=File.ReadList(File.DirRootExte";
mostCurrent._listaconfiguracion = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C/configuracion","Modelo_F1C_"+mostCurrent._nombre_fichero);
 //BA.debugLineNum = 327;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 330;BA.debugLine="If listaconfiguracion.Get(0)=\"0\" Then";
if ((mostCurrent._listaconfiguracion.Get((int) (0))).equals((Object)("0"))) { 
 //BA.debugLineNum = 331;BA.debugLine="cbled.Checked=False";
mostCurrent._cbled.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 334;BA.debugLine="If listaconfiguracion.Get(0)=\"1\" Then";
if ((mostCurrent._listaconfiguracion.Get((int) (0))).equals((Object)("1"))) { 
 //BA.debugLineNum = 335;BA.debugLine="cbled.Checked=True";
mostCurrent._cbled.setChecked(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 338;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 339;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (0)))+"  INICIO");
 //BA.debugLineNum = 340;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 341;BA.debugLine="lvDatos.AddSingleLine(\"CORTE.MOTOR 2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("CORTE.MOTOR 2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (1)))+"  INICIO");
 //BA.debugLineNum = 342;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 343;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (2)))+"  INICIO");
 //BA.debugLineNum = 344;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 345;BA.debugLine="lvDatos.AddSingleLine(\"TIMON DIREC 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("TIMON DIREC 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (3)))+"  INICIO");
 //BA.debugLineNum = 346;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 347;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (4)))+"  INICIO");
 //BA.debugLineNum = 352;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColo";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 353;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (5)))+"  RDT");
 //BA.debugLineNum = 354;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 355;BA.debugLine="lvDatos.AddSingleLine(\"CORTE.MOTOR 2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("CORTE.MOTOR 2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (6)))+"  RDT");
 //BA.debugLineNum = 356;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 357;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (7)))+"  RDT");
 //BA.debugLineNum = 358;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 359;BA.debugLine="lvDatos.AddSingleLine(\"TIMON DIREC 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("TIMON DIREC 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (8)))+"  RDT");
 //BA.debugLineNum = 360;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 361;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (9)))+"  RDT");
 //BA.debugLineNum = 364;BA.debugLine="Log(\"Tamaño Lista \"&lista1.Size)";
anywheresoftware.b4a.keywords.Common.Log("Tamaño Lista "+BA.NumberToString(mostCurrent._lista1.getSize()));
 //BA.debugLineNum = 366;BA.debugLine="For i=10 To lista1.Size-1 Step 4";
{
final int step60 = (int) (4);
final int limit60 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (10) ; (step60 > 0 && _i <= limit60) || (step60 < 0 && _i >= limit60); _i = ((int)(0 + _i + step60)) ) {
 //BA.debugLineNum = 367;BA.debugLine="tiempo_aux=lista1.Get(i)";
mostCurrent._tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get(_i));
 //BA.debugLineNum = 368;BA.debugLine="tiempo_minimo=lista1.get(i)";
_tiempo_minimo = (int)(BA.ObjectToNumber(mostCurrent._lista1.Get(_i)));
 //BA.debugLineNum = 369;BA.debugLine="Log(\"Tiempo actual \" &tiempo_minimo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo actual "+BA.NumberToString(_tiempo_minimo));
 //BA.debugLineNum = 370;BA.debugLine="tipo_tiempo_aux=lista1.Get(i+1)";
mostCurrent._tipo_tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+1)));
 //BA.debugLineNum = 371;BA.debugLine="servo_aux=lista1.Get(i+2)";
mostCurrent._servo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+2)));
 //BA.debugLineNum = 373;BA.debugLine="If tipo_tiempo_aux=0 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 374;BA.debugLine="tiempo_minimo=tiempo_minimo";
_tiempo_minimo = _tiempo_minimo;
 };
 //BA.debugLineNum = 378;BA.debugLine="If tipo_tiempo_aux=1 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 379;BA.debugLine="tiempo_minimo=tiempo_minimo*10";
_tiempo_minimo = (int) (_tiempo_minimo*10);
 };
 //BA.debugLineNum = 383;BA.debugLine="If tipo_tiempo_aux=2 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 384;BA.debugLine="tiempo_minimo=tiempo_minimo*600";
_tiempo_minimo = (int) (_tiempo_minimo*600);
 };
 //BA.debugLineNum = 391;BA.debugLine="If servo_aux=1 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 392;BA.debugLine="servo_aux_nombre=\"C.INCI.ALAS 1\"";
mostCurrent._servo_aux_nombre = "C.INCI.ALAS 1";
 };
 //BA.debugLineNum = 394;BA.debugLine="If servo_aux=2 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 395;BA.debugLine="servo_aux_nombre=\"CORTE.MOTOR 2\"";
mostCurrent._servo_aux_nombre = "CORTE.MOTOR 2";
 };
 //BA.debugLineNum = 397;BA.debugLine="If servo_aux=3 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 398;BA.debugLine="servo_aux_nombre=\"INC.STABILO 3\"";
mostCurrent._servo_aux_nombre = "INC.STABILO 3";
 };
 //BA.debugLineNum = 400;BA.debugLine="If servo_aux=4 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 401;BA.debugLine="servo_aux_nombre=\"TIMON DIREC 4\"";
mostCurrent._servo_aux_nombre = "TIMON DIREC 4";
 };
 //BA.debugLineNum = 403;BA.debugLine="If servo_aux=5 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 404;BA.debugLine="servo_aux_nombre=\"FLAP-FOLDER 5\"";
mostCurrent._servo_aux_nombre = "FLAP-FOLDER 5";
 };
 //BA.debugLineNum = 409;BA.debugLine="giro_aux=lista1.Get(i+3)";
mostCurrent._giro_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+3)));
 //BA.debugLineNum = 410;BA.debugLine="If tipo_tiempo_aux=\"0\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("0")) { 
 //BA.debugLineNum = 411;BA.debugLine="tipo_tiempo_aux=\"DECIMAS\"";
mostCurrent._tipo_tiempo_aux = "DECIMAS";
 };
 //BA.debugLineNum = 414;BA.debugLine="If tipo_tiempo_aux=\"1\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("1")) { 
 //BA.debugLineNum = 415;BA.debugLine="tipo_tiempo_aux=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo_aux = "SEGUNDOS";
 };
 //BA.debugLineNum = 418;BA.debugLine="If tipo_tiempo_aux=\"2\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("2")) { 
 //BA.debugLineNum = 419;BA.debugLine="tipo_tiempo_aux=\"MINUTOS\"";
mostCurrent._tipo_tiempo_aux = "MINUTOS";
 };
 //BA.debugLineNum = 421;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 422;BA.debugLine="lvDatos.AddSingleLine(servo_aux_nombre&\"   Gir";
mostCurrent._lvdatos.AddSingleLine(mostCurrent._servo_aux_nombre+"   Giro: "+mostCurrent._giro_aux+"    Tiempo: "+mostCurrent._tiempo_aux+"  Tipo tiempo: "+mostCurrent._tipo_tiempo_aux);
 }
};
 //BA.debugLineNum = 425;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 426;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 427;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 428;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 429;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 430;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 431;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 432;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 433;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 434;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 435;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 436;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 437;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 438;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 439;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 440;BA.debugLine="contador_lineas=12";
_contador_lineas = (int) (12);
 //BA.debugLineNum = 441;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 442;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 443;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 444;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 445;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 446;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 447;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 448;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 449;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 450;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 457;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 } 
       catch (Exception e344) {
			processBA.setLastException(e344); //BA.debugLineNum = 463;BA.debugLine="If File.Exists(File.DirRootExternal & \"/Consola/";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 464;BA.debugLine="lista1.Clear";
mostCurrent._lista1.Clear();
 //BA.debugLineNum = 467;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 468;BA.debugLine="strModelo=\"Modelo_F1B_\"&NOMBRE_FICHERO";
_strmodelo = "Modelo_F1B_"+mostCurrent._nombre_fichero;
 //BA.debugLineNum = 469;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 470;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 471;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 472;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 473;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 474;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 475;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 476;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 477;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 478;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 479;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 480;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 481;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 482;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 483;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 484;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 //BA.debugLineNum = 485;BA.debugLine="lbNumeroServo.Text=\"C.I.ALA\"";
mostCurrent._lbnumeroservo.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 486;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 487;BA.debugLine="btOK.Enabled=True";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 488;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 489;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 490;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 491;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 492;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 493;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 494;BA.debugLine="btOK.Visible=True";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 496;BA.debugLine="tiempo_actual=0";
_tiempo_actual = (int) (0);
 //BA.debugLineNum = 497;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 498;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 };
 //BA.debugLineNum = 501;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 };
 //BA.debugLineNum = 507;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 143;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 145;BA.debugLine="Try";
try { //BA.debugLineNum = 146;BA.debugLine="Activity.LoadLayout(\"f1c1\")";
mostCurrent._activity.LoadLayout("f1c1",mostCurrent.activityBA);
 //BA.debugLineNum = 147;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 148;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 149;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 150;BA.debugLine="timer1.Initialize(\"Timer1\",1000)";
mostCurrent._timer1.Initialize(processBA,"Timer1",(long) (1000));
 //BA.debugLineNum = 151;BA.debugLine="timer1.Enabled=True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 153;BA.debugLine="tim.Initialize(\"tim\",500)";
mostCurrent._tim.Initialize(processBA,"tim",(long) (500));
 //BA.debugLineNum = 154;BA.debugLine="tim.Enabled=True";
mostCurrent._tim.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 155;BA.debugLine="ime1.SetCustomFilter(etTiempo,etTiempo.INPUT_TYP";
mostCurrent._ime1.SetCustomFilter((android.widget.EditText)(mostCurrent._ettiempo.getObject()),mostCurrent._ettiempo.INPUT_TYPE_DECIMAL_NUMBERS,"0123456789");
 //BA.debugLineNum = 156;BA.debugLine="ime1.SetCustomFilter(etGiro,etGiro.INPUT_TYPE_DEC";
mostCurrent._ime1.SetCustomFilter((android.widget.EditText)(mostCurrent._etgiro.getObject()),mostCurrent._etgiro.INPUT_TYPE_DECIMAL_NUMBERS,"0123456789");
 //BA.debugLineNum = 162;BA.debugLine="directorio=File.DirRootExternal & \"/Consola\"";
mostCurrent._directorio = anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola";
 //BA.debugLineNum = 163;BA.debugLine="File.MakeDir(File.DirRootExternal & \"/Consola\",\"F1";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola","F1C");
 //BA.debugLineNum = 164;BA.debugLine="File.MakeDir(File.DirRootExternal & \"/Consola/F1C\"";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","configuracion");
 //BA.debugLineNum = 165;BA.debugLine="BC1.Initialize(Colors.White,5)";
mostCurrent._bc1.Initialize(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (5));
 //BA.debugLineNum = 166;BA.debugLine="ime1.Initialize(\"\")";
mostCurrent._ime1.Initialize("");
 //BA.debugLineNum = 167;BA.debugLine="InitPanel(Panel1,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel1,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 168;BA.debugLine="InitPanel(Panel2,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel2,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 169;BA.debugLine="InitPanel(Panel3,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel3,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 170;BA.debugLine="InitPanel(Panel4,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel4,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 171;BA.debugLine="label10.Text=\"F1-C\"";
mostCurrent._label10.setText((Object)("F1-C"));
 //BA.debugLineNum = 173;BA.debugLine="BC.Initialize(Colors.Blue,5)";
mostCurrent._bc.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Blue,(int) (5));
 //BA.debugLineNum = 176;BA.debugLine="spServo.Add(\"PULSE AQUI\")";
mostCurrent._spservo.Add("PULSE AQUI");
 //BA.debugLineNum = 177;BA.debugLine="spServo.Add(\"C.I.ALA\")";
mostCurrent._spservo.Add("C.I.ALA");
 //BA.debugLineNum = 178;BA.debugLine="spServo.Add(\"MOTOR\")";
mostCurrent._spservo.Add("MOTOR");
 //BA.debugLineNum = 179;BA.debugLine="spServo.Add(\"STABILO\")";
mostCurrent._spservo.Add("STABILO");
 //BA.debugLineNum = 180;BA.debugLine="spServo.Add(\"TIMON\")";
mostCurrent._spservo.Add("TIMON");
 //BA.debugLineNum = 181;BA.debugLine="spServo.Add(\"FLAP|FOLDER\")";
mostCurrent._spservo.Add("FLAP|FOLDER");
 //BA.debugLineNum = 184;BA.debugLine="spTiempo.Add(\"PULSE AQUI\")";
mostCurrent._sptiempo.Add("PULSE AQUI");
 //BA.debugLineNum = 185;BA.debugLine="spTiempo.Add(\"DECIMAS\")";
mostCurrent._sptiempo.Add("DECIMAS");
 //BA.debugLineNum = 186;BA.debugLine="spTiempo.Add(\"SEGUNDOS\")";
mostCurrent._sptiempo.Add("SEGUNDOS");
 //BA.debugLineNum = 187;BA.debugLine="spTiempo.Add(\"MINUTOS\")";
mostCurrent._sptiempo.Add("MINUTOS");
 //BA.debugLineNum = 189;BA.debugLine="spconfig.Add(\"PULSE AQUI\")";
mostCurrent._spconfig.Add("PULSE AQUI");
 //BA.debugLineNum = 190;BA.debugLine="spconfig.Add(\"PRUEBA\")";
mostCurrent._spconfig.Add("PRUEBA");
 //BA.debugLineNum = 191;BA.debugLine="spconfig.Add(\"PRIMERA HORA\")";
mostCurrent._spconfig.Add("PRIMERA HORA");
 //BA.debugLineNum = 192;BA.debugLine="spconfig.Add(\"VIENTO ALTO\")";
mostCurrent._spconfig.Add("VIENTO ALTO");
 //BA.debugLineNum = 193;BA.debugLine="spconfig.Add(\"VIENTO MEDIO\")";
mostCurrent._spconfig.Add("VIENTO MEDIO");
 //BA.debugLineNum = 194;BA.debugLine="spconfig.Add(\"CALMA\")";
mostCurrent._spconfig.Add("CALMA");
 //BA.debugLineNum = 195;BA.debugLine="spconfig.Add(\"TERMICA\")";
mostCurrent._spconfig.Add("TERMICA");
 //BA.debugLineNum = 196;BA.debugLine="spconfig.TextSize=23";
mostCurrent._spconfig.setTextSize((float) (23));
 //BA.debugLineNum = 198;BA.debugLine="spServo.DropdownBackgroundColor=Colors.White";
mostCurrent._spservo.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 199;BA.debugLine="spTiempo.DropdownBackgroundColor=Colors.White";
mostCurrent._sptiempo.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 200;BA.debugLine="spconfig.DropdownBackgroundColor=Colors.White";
mostCurrent._spconfig.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 202;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 203;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 204;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 205;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 206;BA.debugLine="Panel2.Visible=False";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 207;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 208;BA.debugLine="Label5.Visible=False";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 209;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 210;BA.debugLine="lbservotxt.Visible=False";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 213;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 214;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 215;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 216;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 217;BA.debugLine="lbNumeroServo.Text=\"C.I.ALA\"";
mostCurrent._lbnumeroservo.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 218;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 219;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 220;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 //BA.debugLineNum = 221;BA.debugLine="lista1.Initialize";
mostCurrent._lista1.Initialize();
 //BA.debugLineNum = 222;BA.debugLine="listaconfiguracion.Initialize2(Array As String(";
mostCurrent._listaconfiguracion.Initialize2(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"0","nombre"}));
 //BA.debugLineNum = 223;BA.debugLine="listaconfiguracion.Set(1,NOMBRE_FICHERO)";
mostCurrent._listaconfiguracion.Set((int) (1),(Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 224;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consol";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C/configuracion","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._listaconfiguracion);
 //BA.debugLineNum = 227;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 228;BA.debugLine="spconfig.Enabled=False";
mostCurrent._spconfig.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 229;BA.debugLine="lbModelo.Text=\"PULSE AQUI\"";
mostCurrent._lbmodelo.setText((Object)("PULSE AQUI"));
 //BA.debugLineNum = 230;BA.debugLine="openusb";
_openusb();
 } 
       catch (Exception e172) {
			processBA.setLastException(e172); };
 //BA.debugLineNum = 234;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 2440;BA.debugLine="Sub Activity_KeyPress(KeyCode As Int) As Boolean";
 //BA.debugLineNum = 2442;BA.debugLine="If dlgFileExpl.IsInitialized Then";
if (mostCurrent._dlgfileexpl.IsInitialized()) { 
 //BA.debugLineNum = 2443;BA.debugLine="If dlgFileExpl.IsActive Then Return True";
if (mostCurrent._dlgfileexpl._isactive()) { 
if (true) return anywheresoftware.b4a.keywords.Common.True;};
 };
 //BA.debugLineNum = 2445;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 2446;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 513;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 515;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 509;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 510;BA.debugLine="flag=1";
_flag = (int) (1);
 //BA.debugLineNum = 511;BA.debugLine="End Sub";
return "";
}
public static String  _astreams1_error() throws Exception{
 //BA.debugLineNum = 2828;BA.debugLine="Sub AStreams1_Error";
 //BA.debugLineNum = 2829;BA.debugLine="Log(\"Error: \" & LastException)";
anywheresoftware.b4a.keywords.Common.Log("Error: "+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 //BA.debugLineNum = 2831;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 2832;BA.debugLine="ToastMessageShow(\"ERROR EN LA LECTURA\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("ERROR EN LA LECTURA",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2833;BA.debugLine="astreams1.Close";
_astreams1.Close();
 //BA.debugLineNum = 2834;BA.debugLine="End Sub";
return "";
}
public static String  _astreams1_newdata(byte[] _buffer) throws Exception{
String _msg = "";
byte[] _b = null;
int[] _c = null;
String[] _nombre_leer = null;
int _result = 0;
int _i = 0;
 //BA.debugLineNum = 2711;BA.debugLine="Sub Astreams1_NewData (buffer() As Byte)";
 //BA.debugLineNum = 2712;BA.debugLine="Dim msg As String=\" \"";
_msg = " ";
 //BA.debugLineNum = 2713;BA.debugLine="Dim b(255) As Byte";
_b = new byte[(int) (255)];
;
 //BA.debugLineNum = 2714;BA.debugLine="Dim c(50) As Int";
_c = new int[(int) (50)];
;
 //BA.debugLineNum = 2715;BA.debugLine="Dim nombre_leer(50) As String";
_nombre_leer = new String[(int) (50)];
java.util.Arrays.fill(_nombre_leer,"");
 //BA.debugLineNum = 2716;BA.debugLine="Log(\"estamos en buffer 0 \"&buffer(0))";
anywheresoftware.b4a.keywords.Common.Log("estamos en buffer 0 "+BA.NumberToString(_buffer[(int) (0)]));
 //BA.debugLineNum = 2719;BA.debugLine="If buffer(0)=76 Then";
if (_buffer[(int) (0)]==76) { 
 //BA.debugLineNum = 2721;BA.debugLine="If flag_rx=False Then";
if (_flag_rx==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 2722;BA.debugLine="Log(\"LECTURA DE BYTES\")";
anywheresoftware.b4a.keywords.Common.Log("LECTURA DE BYTES");
 //BA.debugLineNum = 2723;BA.debugLine="flag_rx=True";
_flag_rx = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 2724;BA.debugLine="contador=0";
_contador = (int) (0);
 };
 };
 //BA.debugLineNum = 2729;BA.debugLine="If flag_rx=True Then";
if (_flag_rx==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 2731;BA.debugLine="contador=contador+1";
_contador = (int) (_contador+1);
 //BA.debugLineNum = 2733;BA.debugLine="If contador>224 Then";
if (_contador>224) { 
 //BA.debugLineNum = 2735;BA.debugLine="b(contador-225)=buffer(0)";
_b[(int) (_contador-225)] = _buffer[(int) (0)];
 //BA.debugLineNum = 2736;BA.debugLine="c(contador-225)=buffer(0)";
_c[(int) (_contador-225)] = (int) (_buffer[(int) (0)]);
 //BA.debugLineNum = 2738;BA.debugLine="If buffer(0)<> 0 Then";
if (_buffer[(int) (0)]!=0) { 
 //BA.debugLineNum = 2739;BA.debugLine="nombre_leer(contador-225)=Chr(c(contador-225))";
_nombre_leer[(int) (_contador-225)] = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(_c[(int) (_contador-225)]));
 //BA.debugLineNum = 2740;BA.debugLine="nombre_leido=nombre_leido&Chr(c(contador-225))";
mostCurrent._nombre_leido = mostCurrent._nombre_leido+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(_c[(int) (_contador-225)]));
 //BA.debugLineNum = 2741;BA.debugLine="Log(nombre_leido)";
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._nombre_leido);
 };
 };
 //BA.debugLineNum = 2747;BA.debugLine="If contador=255 Then";
if (_contador==255) { 
 //BA.debugLineNum = 2748;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 2750;BA.debugLine="Log(\"msg\"&	 BytesToString(b,0,b.Length,\"UTF8\"))";
anywheresoftware.b4a.keywords.Common.Log("msg"+anywheresoftware.b4a.keywords.Common.BytesToString(_b,(int) (0),_b.length,"UTF8"));
 //BA.debugLineNum = 2751;BA.debugLine="Log(\"FIN RX\")";
anywheresoftware.b4a.keywords.Common.Log("FIN RX");
 //BA.debugLineNum = 2754;BA.debugLine="flag_rx=False";
_flag_rx = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 2755;BA.debugLine="If File.Exists(File.DirRootExternal & \"/Consola";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_leido)) { 
 //BA.debugLineNum = 2756;BA.debugLine="Dim result As Int";
_result = 0;
 //BA.debugLineNum = 2757;BA.debugLine="result=Msgbox2(\"Desea abrir el fichero \"&nombr";
_result = anywheresoftware.b4a.keywords.Common.Msgbox2("Desea abrir el fichero "+mostCurrent._nombre_leido+"?","Abrir Fichero","Si","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 2758;BA.debugLine="If result=DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 2759;BA.debugLine="ToastMessageShow(\"Abrimos\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Abrimos",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2760;BA.debugLine="Log(lbModelo.Text)";
anywheresoftware.b4a.keywords.Common.Log(mostCurrent._lbmodelo.getText());
 //BA.debugLineNum = 2761;BA.debugLine="If nombre_leido.Contains(lbModelo.Text) Then";
if (mostCurrent._nombre_leido.contains(mostCurrent._lbmodelo.getText())) { 
 //BA.debugLineNum = 2762;BA.debugLine="ToastMessageShow(\"estamos en el\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("estamos en el",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 2765;BA.debugLine="Abrir_Fichero_leido(nombre_leido)";
_abrir_fichero_leido(mostCurrent._nombre_leido);
 };
 };
 //BA.debugLineNum = 2769;BA.debugLine="If result=DialogResponse.NEGATIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
 //BA.debugLineNum = 2770;BA.debugLine="ToastMessageShow(\"Cerramos\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Cerramos",anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 2773;BA.debugLine="Msgbox(\"No exite el fichero\",\"Error\")";
anywheresoftware.b4a.keywords.Common.Msgbox("No exite el fichero","Error",mostCurrent.activityBA);
 };
 };
 };
 //BA.debugLineNum = 2782;BA.debugLine="If flag_rx=False Then";
if (_flag_rx==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 2783;BA.debugLine="If buffer(0)=69 Then";
if (_buffer[(int) (0)]==69) { 
 //BA.debugLineNum = 2784;BA.debugLine="Leer_fichero";
_leer_fichero();
 //BA.debugLineNum = 2785;BA.debugLine="Log(\"GRABACION DE BYTES\")";
anywheresoftware.b4a.keywords.Common.Log("GRABACION DE BYTES");
 //BA.debugLineNum = 2787;BA.debugLine="astreams1.Write2(buffer_tx,0,191)";
_astreams1.Write2(_buffer_tx,(int) (0),(int) (191));
 //BA.debugLineNum = 2789;BA.debugLine="astreams1.Write2(buffer_tx,192,64)";
_astreams1.Write2(_buffer_tx,(int) (192),(int) (64));
 //BA.debugLineNum = 2790;BA.debugLine="For i=0 To buffer_tx.Length-1";
{
final int step55 = 1;
final int limit55 = (int) (_buffer_tx.length-1);
for (_i = (int) (0) ; (step55 > 0 && _i <= limit55) || (step55 < 0 && _i >= limit55); _i = ((int)(0 + _i + step55)) ) {
 //BA.debugLineNum = 2791;BA.debugLine="Log(\"buffer grab: \"&buffer_tx(i))";
anywheresoftware.b4a.keywords.Common.Log("buffer grab: "+BA.NumberToString(_buffer_tx[_i]));
 }
};
 };
 };
 //BA.debugLineNum = 2795;BA.debugLine="If flag_rx=False Then";
if (_flag_rx==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 2796;BA.debugLine="If buffer(0)=77 Then";
if (_buffer[(int) (0)]==77) { 
 //BA.debugLineNum = 2797;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 2798;BA.debugLine="Msgbox(\"Modelo no corresponde con modalidad\",\"E";
anywheresoftware.b4a.keywords.Common.Msgbox("Modelo no corresponde con modalidad","Error",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 2800;BA.debugLine="If buffer(0)=70 Then";
if (_buffer[(int) (0)]==70) { 
 //BA.debugLineNum = 2801;BA.debugLine="Log(\"FIN GRABACION\")";
anywheresoftware.b4a.keywords.Common.Log("FIN GRABACION");
 //BA.debugLineNum = 2802;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 };
 };
 //BA.debugLineNum = 2806;BA.debugLine="End Sub";
return "";
}
public static String  _astreams1_terminated() throws Exception{
 //BA.debugLineNum = 2836;BA.debugLine="Sub Astreams1_Terminated";
 //BA.debugLineNum = 2837;BA.debugLine="Log(\"Terminated\")";
anywheresoftware.b4a.keywords.Common.Log("Terminated");
 //BA.debugLineNum = 2838;BA.debugLine="astreams1.Close";
_astreams1.Close();
 //BA.debugLineNum = 2839;BA.debugLine="End Sub";
return "";
}
public static String  _borrar() throws Exception{
anywheresoftware.b4a.objects.collections.List _filestodelete = null;
int _i = 0;
 //BA.debugLineNum = 2571;BA.debugLine="Sub borrar";
 //BA.debugLineNum = 2572;BA.debugLine="Dim FilesToDelete As List";
_filestodelete = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 2574;BA.debugLine="FilesToDelete.Initialize";
_filestodelete.Initialize();
 //BA.debugLineNum = 2575;BA.debugLine="FilesToDelete.AddAll(File.ListFiles(File.DirRootEx";
_filestodelete.AddAll(anywheresoftware.b4a.keywords.Common.File.ListFiles(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C"));
 //BA.debugLineNum = 2577;BA.debugLine="For I = 0 To FilesToDelete.Size -1";
{
final int step4 = 1;
final int limit4 = (int) (_filestodelete.getSize()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 2579;BA.debugLine="File.Delete(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C",BA.ObjectToString(_filestodelete.Get(_i)));
 }
};
 //BA.debugLineNum = 2583;BA.debugLine="End Sub";
return "";
}
public static String  _borrar_datos(int _valor_ini,int _valor_fin,int _valor_sum) throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
 //BA.debugLineNum = 2520;BA.debugLine="Sub borrar_datos (valor_ini As Int, valor_fin As I";
 //BA.debugLineNum = 2521;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 2522;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 2523;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 2524;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 2525;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 2529;BA.debugLine="If posicion_mod<valor_fin Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<_valor_fin) { 
 //BA.debugLineNum = 2530;BA.debugLine="If posicion_mod>=valor_ini Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=_valor_ini) { 
 //BA.debugLineNum = 2531;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 2532;BA.debugLine="pos_valor=(pos_mod_aux*4)+valor_sum";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+_valor_sum);
 //BA.debugLineNum = 2535;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 2536;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 2537;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 2538;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 };
 };
 //BA.debugLineNum = 2550;BA.debugLine="End Sub";
return "";
}
public static String  _btcomenzar_longclick() throws Exception{
 //BA.debugLineNum = 2594;BA.debugLine="Sub btComenzar_LongClick";
 //BA.debugLineNum = 2595;BA.debugLine="flag=2";
_flag = (int) (2);
 //BA.debugLineNum = 2596;BA.debugLine="Log(\"flag F1C: \"&flag)";
anywheresoftware.b4a.keywords.Common.Log("flag F1C: "+BA.NumberToString(_flag));
 //BA.debugLineNum = 2598;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 2599;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 2600;BA.debugLine="End Sub";
return "";
}
public static String  _btescribirmemoria_click() throws Exception{
 //BA.debugLineNum = 2842;BA.debugLine="Sub btEscribirMemoria_Click";
 //BA.debugLineNum = 2843;BA.debugLine="flag_rx=False";
_flag_rx = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 2844;BA.debugLine="send_data(\"E\")";
_send_data("E");
 //BA.debugLineNum = 2850;BA.debugLine="End Sub";
return "";
}
public static String  _btgrabar_longclick() throws Exception{
 //BA.debugLineNum = 2602;BA.debugLine="Sub btgrabar_LongClick";
 //BA.debugLineNum = 2622;BA.debugLine="If astreams1.IsInitialized Then";
if (_astreams1.IsInitialized()) { 
 }else {
 //BA.debugLineNum = 2625;BA.debugLine="astreams1.Initialize(usb1.GetInputStream, usb1";
_astreams1.Initialize(processBA,_usb1.GetInputStream(),_usb1.GetOutputStream(),"astreams1");
 };
 //BA.debugLineNum = 2628;BA.debugLine="flag_d=False";
_flag_d = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 2630;BA.debugLine="flag_lectura=False";
_flag_lectura = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 2633;BA.debugLine="flag_rx=False";
_flag_rx = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 2634;BA.debugLine="send_data(\"E\")";
_send_data("E");
 //BA.debugLineNum = 2635;BA.debugLine="ProgressDialogShow(\"Grabando fichero...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Grabando fichero...");
 //BA.debugLineNum = 2643;BA.debugLine="End Sub";
return "";
}
public static String  _btleer_click() throws Exception{
 //BA.debugLineNum = 2924;BA.debugLine="Sub btleer_Click";
 //BA.debugLineNum = 2942;BA.debugLine="If astreams1.IsInitialized Then";
if (_astreams1.IsInitialized()) { 
 }else {
 //BA.debugLineNum = 2945;BA.debugLine="astreams1.Initialize(usb1.GetInputStream, usb1";
_astreams1.Initialize(processBA,_usb1.GetInputStream(),_usb1.GetOutputStream(),"astreams1");
 };
 //BA.debugLineNum = 2953;BA.debugLine="flag_lectura=True";
_flag_lectura = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 2954;BA.debugLine="flag_rx=False";
_flag_rx = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 2955;BA.debugLine="send_data(\"L\")";
_send_data("L");
 //BA.debugLineNum = 2956;BA.debugLine="ProgressDialogShow(\"Leyendo informacion, Por fa";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"Leyendo informacion, Por favor espere....");
 //BA.debugLineNum = 2964;BA.debugLine="nombre_leido=\"\"";
mostCurrent._nombre_leido = "";
 //BA.debugLineNum = 2965;BA.debugLine="End Sub";
return "";
}
public static String  _btnclose_click() throws Exception{
 //BA.debugLineNum = 2820;BA.debugLine="Sub btnClose_Click";
 //BA.debugLineNum = 2821;BA.debugLine="astreams1.Close";
_astreams1.Close();
 //BA.debugLineNum = 2822;BA.debugLine="usb1.Close";
_usb1.Close();
 //BA.debugLineNum = 2826;BA.debugLine="End Sub";
return "";
}
public static String  _btok_click() throws Exception{
String _nom_ser = "";
String _aux_valor_giro = "";
int _i = 0;
 //BA.debugLineNum = 2148;BA.debugLine="Sub btOK_Click";
 //BA.debugLineNum = 2149;BA.debugLine="Dim nom_ser As String";
_nom_ser = "";
 //BA.debugLineNum = 2150;BA.debugLine="Try";
try { //BA.debugLineNum = 2152;BA.debugLine="Log(\"giro en btok \" & giro)";
anywheresoftware.b4a.keywords.Common.Log("giro en btok "+mostCurrent._giro);
 //BA.debugLineNum = 2154;BA.debugLine="If giro=\"\" Then";
if ((mostCurrent._giro).equals("")) { 
 }else {
 //BA.debugLineNum = 2158;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 2160;BA.debugLine="If contador_lineas<=5 Then";
if (_contador_lineas<=5) { 
 //BA.debugLineNum = 2161;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2162;BA.debugLine="nom_ser=\"C.INCI.ALAS\"";
_nom_ser = "C.INCI.ALAS";
 };
 //BA.debugLineNum = 2164;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 2165;BA.debugLine="nom_ser=\"CORTE MOTOR\"";
_nom_ser = "CORTE MOTOR";
 };
 //BA.debugLineNum = 2167;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 2168;BA.debugLine="nom_ser=\"INC.STABILO\"";
_nom_ser = "INC.STABILO";
 };
 //BA.debugLineNum = 2170;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 2171;BA.debugLine="nom_ser=\"TIMON DIREC\"";
_nom_ser = "TIMON DIREC";
 };
 //BA.debugLineNum = 2173;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 2174;BA.debugLine="nom_ser=\"FLAP-FOLDER\"";
_nom_ser = "FLAP-FOLDER";
 };
 //BA.debugLineNum = 2176;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 2178;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2179;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2181;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 2182;BA.debugLine="lbservotxt.Text=\"MOTOR\"";
mostCurrent._lbservotxt.setText((Object)("MOTOR"));
 };
 //BA.debugLineNum = 2184;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 2185;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2187;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 2188;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2190;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 2191;BA.debugLine="lbservotxt.Text=\"FLAP-FOLDER\"";
mostCurrent._lbservotxt.setText((Object)("FLAP-FOLDER"));
 };
 //BA.debugLineNum = 2196;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2197;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 2198;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2202;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2203;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2204;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2205;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2210;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2212;BA.debugLine="lista1.Add(giro)";
mostCurrent._lista1.Add((Object)(mostCurrent._giro));
 };
 //BA.debugLineNum = 2215;BA.debugLine="If contador_lineas=5 Then";
if (_contador_lineas==5) { 
 //BA.debugLineNum = 2216;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2217;BA.debugLine="Label9.Visible=True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2218;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 2219;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 2220;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 }else {
 //BA.debugLineNum = 2222;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2223;BA.debugLine="lbNumeroServo.Text=contador_lineas";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas));
 //BA.debugLineNum = 2224;BA.debugLine="If contador_lineas=1 Then";
if (_contador_lineas==1) { 
 //BA.debugLineNum = 2225;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2227;BA.debugLine="If contador_lineas=2 Then";
if (_contador_lineas==2) { 
 //BA.debugLineNum = 2228;BA.debugLine="lbservotxt.Text=\"MOTOR\"";
mostCurrent._lbservotxt.setText((Object)("MOTOR"));
 };
 //BA.debugLineNum = 2230;BA.debugLine="If contador_lineas=3 Then";
if (_contador_lineas==3) { 
 //BA.debugLineNum = 2231;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2233;BA.debugLine="If contador_lineas=4 Then";
if (_contador_lineas==4) { 
 //BA.debugLineNum = 2234;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2236;BA.debugLine="If contador_lineas=5 Then";
if (_contador_lineas==5) { 
 //BA.debugLineNum = 2237;BA.debugLine="lbservotxt.Text=\"FLAP-FOLDER\"";
mostCurrent._lbservotxt.setText((Object)("FLAP-FOLDER"));
 };
 };
 };
 //BA.debugLineNum = 2246;BA.debugLine="If contador_lineas>=6 Then";
if (_contador_lineas>=6) { 
 //BA.debugLineNum = 2247;BA.debugLine="If contador_lineas<=11 Then";
if (_contador_lineas<=11) { 
 //BA.debugLineNum = 2248;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 2250;BA.debugLine="If contador_lineas>=7 Then";
if (_contador_lineas>=7) { 
 //BA.debugLineNum = 2251;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 2252;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2253;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 2254;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2258;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2259;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2260;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2261;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2266;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2268;BA.debugLine="lista1.Add(giro)";
mostCurrent._lista1.Add((Object)(mostCurrent._giro));
 };
 };
 //BA.debugLineNum = 2273;BA.debugLine="If contador_lineas=11 Then";
if (_contador_lineas==11) { 
 //BA.debugLineNum = 2274;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2275;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2276;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2277;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2278;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2279;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2280;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2281;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2282;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 2283;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 2284;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step108 = 1;
final int limit108 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0) ; (step108 > 0 && _i <= limit108) || (step108 < 0 && _i >= limit108); _i = ((int)(0 + _i + step108)) ) {
 //BA.debugLineNum = 2286;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 2292;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2293;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2294;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2295;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2296;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2297;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2298;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2299;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2300;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2301;BA.debugLine="datos_lv";
_datos_lv();
 //BA.debugLineNum = 2302;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 2305;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 2306;BA.debugLine="lbNumeroServo.Text=contador_lineas-6";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas-6));
 //BA.debugLineNum = 2307;BA.debugLine="If contador_lineas-6=1 Then";
if (_contador_lineas-6==1) { 
 //BA.debugLineNum = 2308;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 2310;BA.debugLine="If contador_lineas-6=2 Then";
if (_contador_lineas-6==2) { 
 //BA.debugLineNum = 2311;BA.debugLine="lbservotxt.Text=\"MOTOR\"";
mostCurrent._lbservotxt.setText((Object)("MOTOR"));
 };
 //BA.debugLineNum = 2313;BA.debugLine="If contador_lineas-6=3 Then";
if (_contador_lineas-6==3) { 
 //BA.debugLineNum = 2314;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 2316;BA.debugLine="If contador_lineas-6=4 Then";
if (_contador_lineas-6==4) { 
 //BA.debugLineNum = 2317;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 2319;BA.debugLine="If contador_lineas-6=5 Then";
if (_contador_lineas-6==5) { 
 //BA.debugLineNum = 2320;BA.debugLine="lbservotxt.Text=\"FLAP-FOLDER\"";
mostCurrent._lbservotxt.setText((Object)("FLAP-FOLDER"));
 };
 };
 };
 };
 //BA.debugLineNum = 2330;BA.debugLine="Log(\"numero lineas  \"&contador_lineas)";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString(_contador_lineas));
 //BA.debugLineNum = 2332;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 2334;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 2335;BA.debugLine="giro=\"\"";
mostCurrent._giro = "";
 };
 } 
       catch (Exception e1667) {
			processBA.setLastException(e1667); };
 //BA.debugLineNum = 2340;BA.debugLine="End Sub";
return "";
}
public static String  _btsalir_longclick() throws Exception{
 //BA.debugLineNum = 2585;BA.debugLine="Sub btSalir_LongClick";
 //BA.debugLineNum = 2587;BA.debugLine="flag=1";
_flag = (int) (1);
 //BA.debugLineNum = 2588;BA.debugLine="Log(\"flag F1C: \"&flag)";
anywheresoftware.b4a.keywords.Common.Log("flag F1C: "+BA.NumberToString(_flag));
 //BA.debugLineNum = 2591;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 2592;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 2009;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 2010;BA.debugLine="etGiro.ForceDoneButton=False";
mostCurrent._etgiro.setForceDoneButton(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2011;BA.debugLine="etTiempo_1";
_ettiempo_1();
 //BA.debugLineNum = 2013;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
int _i = 0;
 //BA.debugLineNum = 670;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 672;BA.debugLine="If spServo.SelectedItem=\"PULSE AQUI\" Then";
if ((mostCurrent._spservo.getSelectedItem()).equals("PULSE AQUI")) { 
 };
 //BA.debugLineNum = 675;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 676;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 677;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 678;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 679;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 681;BA.debugLine="If posicion_mod<10 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<10) { 
 //BA.debugLineNum = 686;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 689;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 690;BA.debugLine="lista1.set(posicion_mod,aux_valor_giro)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 693;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 694;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 696;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 697;BA.debugLine="lista1.set(posicion_mod,aux_valor_giro)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 701;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 702;BA.debugLine="aux_valor_giro=giro";
_aux_valor_giro = mostCurrent._giro;
 //BA.debugLineNum = 703;BA.debugLine="lista1.set(posicion_mod,aux_valor_giro)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 706;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 707;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 708;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 709;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 710;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 711;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 712;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 713;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 714;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 715;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 721;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 722;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 725;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 726;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (0)))+"  INICIO");
 //BA.debugLineNum = 727;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 728;BA.debugLine="lvDatos.AddSingleLine(\"CORTE.MOTOR 2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("CORTE.MOTOR 2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (1)))+"  INICIO");
 //BA.debugLineNum = 729;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 730;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (2)))+"  INICIO");
 //BA.debugLineNum = 731;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 732;BA.debugLine="lvDatos.AddSingleLine(\"TIMON DIREC 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("TIMON DIREC 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (3)))+"  INICIO");
 //BA.debugLineNum = 733;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 734;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (4)))+"  INICIO");
 //BA.debugLineNum = 739;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColo";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 740;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (5)))+"  RDT");
 //BA.debugLineNum = 741;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 742;BA.debugLine="lvDatos.AddSingleLine(\"CORTE.MOTOR 2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("CORTE.MOTOR 2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (6)))+"  RDT");
 //BA.debugLineNum = 743;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 744;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (7)))+"  RDT");
 //BA.debugLineNum = 745;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 746;BA.debugLine="lvDatos.AddSingleLine(\"TIMON DIREC 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("TIMON DIREC 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (8)))+"  RDT");
 //BA.debugLineNum = 747;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 748;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (9)))+"  RDT");
 //BA.debugLineNum = 763;BA.debugLine="For i=10 To lista1.Size-1 Step 4";
{
final int step55 = (int) (4);
final int limit55 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (10) ; (step55 > 0 && _i <= limit55) || (step55 < 0 && _i >= limit55); _i = ((int)(0 + _i + step55)) ) {
 //BA.debugLineNum = 764;BA.debugLine="tiempo_aux=lista1.Get(i)";
mostCurrent._tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get(_i));
 //BA.debugLineNum = 765;BA.debugLine="tipo_tiempo_aux=lista1.Get(i+1)";
mostCurrent._tipo_tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+1)));
 //BA.debugLineNum = 766;BA.debugLine="servo_aux=lista1.Get(i+2)";
mostCurrent._servo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+2)));
 //BA.debugLineNum = 768;BA.debugLine="If servo_aux=1 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 769;BA.debugLine="servo_aux_nombre=\"C.INCI.ALAS 1\"";
mostCurrent._servo_aux_nombre = "C.INCI.ALAS 1";
 };
 //BA.debugLineNum = 771;BA.debugLine="If servo_aux=2 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 772;BA.debugLine="servo_aux_nombre=\"CORTE.MOTOR 2\"";
mostCurrent._servo_aux_nombre = "CORTE.MOTOR 2";
 };
 //BA.debugLineNum = 774;BA.debugLine="If servo_aux=3 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 775;BA.debugLine="servo_aux_nombre=\"INC.STABILO 3\"";
mostCurrent._servo_aux_nombre = "INC.STABILO 3";
 };
 //BA.debugLineNum = 777;BA.debugLine="If servo_aux=4 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 778;BA.debugLine="servo_aux_nombre=\"TIMON DIREC 4\"";
mostCurrent._servo_aux_nombre = "TIMON DIREC 4";
 };
 //BA.debugLineNum = 780;BA.debugLine="If servo_aux=5 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 781;BA.debugLine="servo_aux_nombre=\"FLAP-FOLDER 5\"";
mostCurrent._servo_aux_nombre = "FLAP-FOLDER 5";
 };
 //BA.debugLineNum = 785;BA.debugLine="giro_aux=lista1.Get(i+3)";
mostCurrent._giro_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+3)));
 //BA.debugLineNum = 786;BA.debugLine="If tipo_tiempo_aux=\"0\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("0")) { 
 //BA.debugLineNum = 787;BA.debugLine="tipo_tiempo=\"DECIMAS\"";
mostCurrent._tipo_tiempo = "DECIMAS";
 //BA.debugLineNum = 788;BA.debugLine="tiempo_aux=tiempo_aux";
mostCurrent._tiempo_aux = mostCurrent._tiempo_aux;
 };
 //BA.debugLineNum = 791;BA.debugLine="If tipo_tiempo_aux=1 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 792;BA.debugLine="tipo_tiempo=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo = "SEGUNDOS";
 };
 //BA.debugLineNum = 796;BA.debugLine="If tipo_tiempo_aux=2 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 797;BA.debugLine="tipo_tiempo=\"MINUTOS\"";
mostCurrent._tipo_tiempo = "MINUTOS";
 };
 //BA.debugLineNum = 800;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 801;BA.debugLine="lvDatos.AddSingleLine(servo_aux_nombre&\"   Gir";
mostCurrent._lvdatos.AddSingleLine(mostCurrent._servo_aux_nombre+"   Giro: "+mostCurrent._giro_aux+"    Tiempo: "+mostCurrent._tiempo_aux+"  Tipo tiempo: "+mostCurrent._tipo_tiempo);
 }
};
 //BA.debugLineNum = 804;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 805;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 806;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 807;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 810;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 811;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 812;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 813;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 814;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 815;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 816;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 817;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 818;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 819;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 820;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 821;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 822;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 825;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 826;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 827;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 832;BA.debugLine="etTiempo_2";
_ettiempo_2();
 //BA.debugLineNum = 835;BA.debugLine="End Sub";
return "";
}
public static String  _cbled_checkedchange(boolean _checked) throws Exception{
int _i = 0;
 //BA.debugLineNum = 2905;BA.debugLine="Sub cbled_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 2906;BA.debugLine="If Checked=True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 2907;BA.debugLine="cbled.Text=\"Led ON\"";
mostCurrent._cbled.setText((Object)("Led ON"));
 //BA.debugLineNum = 2909;BA.debugLine="listaconfiguracion.Set(0,\"1\")";
mostCurrent._listaconfiguracion.Set((int) (0),(Object)("1"));
 }else {
 //BA.debugLineNum = 2912;BA.debugLine="cbled.Text=\"Led OFF\"";
mostCurrent._cbled.setText((Object)("Led OFF"));
 //BA.debugLineNum = 2913;BA.debugLine="listaconfiguracion.Set(0,\"0\")";
mostCurrent._listaconfiguracion.Set((int) (0),(Object)("0"));
 };
 //BA.debugLineNum = 2916;BA.debugLine="For i=0 To listaconfiguracion.Size-1";
{
final int step8 = 1;
final int limit8 = (int) (mostCurrent._listaconfiguracion.getSize()-1);
for (_i = (int) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((int)(0 + _i + step8)) ) {
 //BA.debugLineNum = 2917;BA.debugLine="Log(listaconfiguracion.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._listaconfiguracion.Get(_i)));
 }
};
 //BA.debugLineNum = 2919;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consol";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C/configuracion","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._listaconfiguracion);
 //BA.debugLineNum = 2921;BA.debugLine="End Sub";
return "";
}
public static String  _datos_lv() throws Exception{
 //BA.debugLineNum = 2369;BA.debugLine="Sub datos_lv";
 //BA.debugLineNum = 2372;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2373;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (0)))+"  INICIO");
 //BA.debugLineNum = 2374;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2375;BA.debugLine="lvDatos.AddSingleLine(\"CORTE.MOTOR 2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("CORTE.MOTOR 2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (1)))+"  INICIO");
 //BA.debugLineNum = 2376;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2377;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (2)))+"  INICIO");
 //BA.debugLineNum = 2378;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2379;BA.debugLine="lvDatos.AddSingleLine(\"TIMON DIREC 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("TIMON DIREC 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (3)))+"  INICIO");
 //BA.debugLineNum = 2380;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2381;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (4)))+"  INICIO");
 //BA.debugLineNum = 2386;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColo";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2387;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (5)))+"  RDT");
 //BA.debugLineNum = 2388;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2389;BA.debugLine="lvDatos.AddSingleLine(\"CORTE.MOTOR 2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("CORTE.MOTOR 2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (6)))+"  RDT");
 //BA.debugLineNum = 2390;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2391;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (7)))+"  RDT");
 //BA.debugLineNum = 2392;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2393;BA.debugLine="lvDatos.AddSingleLine(\"TIMON DIREC 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("TIMON DIREC 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (8)))+"  RDT");
 //BA.debugLineNum = 2394;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2395;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (9)))+"  RDT");
 //BA.debugLineNum = 2415;BA.debugLine="End Sub";
return "";
}
public static String  _elegir_modelo() throws Exception{
int _valor = 0;
 //BA.debugLineNum = 517;BA.debugLine="Sub elegir_modelo";
 //BA.debugLineNum = 518;BA.debugLine="Dim valor As Int";
_valor = 0;
 //BA.debugLineNum = 519;BA.debugLine="dial.Input=\"\"";
mostCurrent._dial.setInput("");
 //BA.debugLineNum = 520;BA.debugLine="valor=	dial.Show(\"\",\"NOMBRE MODELO\",\"Aceptar\",\"\",\"";
_valor = mostCurrent._dial.Show("","NOMBRE MODELO","Aceptar","","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 523;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 524;BA.debugLine="If dial.Response=-1 Then";
if (mostCurrent._dial.getResponse()==-1) { 
 };
 //BA.debugLineNum = 530;BA.debugLine="End Sub";
return "";
}
public static String  _enviar_configuracion() throws Exception{
 //BA.debugLineNum = 2886;BA.debugLine="Sub enviar_configuracion";
 //BA.debugLineNum = 2902;BA.debugLine="End Sub";
return "";
}
public static String  _etgiro_textchanged(String _old,String _new) throws Exception{
int _aux = 0;
String _aux1 = "";
 //BA.debugLineNum = 1968;BA.debugLine="Sub etGiro_TextChanged (Old As String, New As Stri";
 //BA.debugLineNum = 1969;BA.debugLine="Try";
try { //BA.debugLineNum = 1970;BA.debugLine="Dim aux As Int";
_aux = 0;
 //BA.debugLineNum = 1971;BA.debugLine="Dim aux1 As String";
_aux1 = "";
 //BA.debugLineNum = 1978;BA.debugLine="If New=\"\" Then";
if ((_new).equals("")) { 
 //BA.debugLineNum = 1980;BA.debugLine="giro=\"\"";
mostCurrent._giro = "";
 }else {
 //BA.debugLineNum = 1985;BA.debugLine="If New>0 Then";
if ((double)(Double.parseDouble(_new))>0) { 
 //BA.debugLineNum = 1986;BA.debugLine="If New<=200 Then";
if ((double)(Double.parseDouble(_new))<=200) { 
 //BA.debugLineNum = 1987;BA.debugLine="giro=New";
mostCurrent._giro = _new;
 //BA.debugLineNum = 1989;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1990;BA.debugLine="If flag_button2=True Then";
if (_flag_button2==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1991;BA.debugLine="Button2.Enabled=True";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1992;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1993;BA.debugLine="flag_button2=False";
_flag_button2 = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1995;BA.debugLine="Log(\"giro_text : \"&giro)";
anywheresoftware.b4a.keywords.Common.Log("giro_text : "+mostCurrent._giro);
 }else {
 //BA.debugLineNum = 1998;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 };
 }else {
 //BA.debugLineNum = 2001;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 };
 };
 } 
       catch (Exception e1421) {
			processBA.setLastException(e1421); };
 //BA.debugLineNum = 2007;BA.debugLine="End Sub";
return "";
}
public static String  _ettiempo_1() throws Exception{
boolean _ret = false;
String _str = "";
int _aux_tiempo = 0;
 //BA.debugLineNum = 1394;BA.debugLine="Sub etTiempo_1";
 //BA.debugLineNum = 1395;BA.debugLine="Try";
try { //BA.debugLineNum = 1396;BA.debugLine="Dim ret As Boolean";
_ret = false;
 //BA.debugLineNum = 1397;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 1398;BA.debugLine="Dim aux_tiempo As  Int";
_aux_tiempo = 0;
 //BA.debugLineNum = 1409;BA.debugLine="tiempo_actual=etTiempo.Text";
_tiempo_actual = (int)(Double.parseDouble(mostCurrent._ettiempo.getText()));
 //BA.debugLineNum = 1410;BA.debugLine="Log(\"Tiempo Actual sin convertir: \"&tiempo_actual)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual sin convertir: "+BA.NumberToString(_tiempo_actual));
 //BA.debugLineNum = 1411;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1412;BA.debugLine="tiempo_actual=tiempo_actual";
_tiempo_actual = _tiempo_actual;
 //BA.debugLineNum = 1413;BA.debugLine="Log(\"Tiempo Actual decimas convertir decimas: \"&ti";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual decimas convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1416;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1417;BA.debugLine="tiempo_actual=tiempo_actual*10";
_tiempo_actual = (int) (_tiempo_actual*10);
 //BA.debugLineNum = 1418;BA.debugLine="Log(\"Tiempo Actual segundos convertir decimas: \"&t";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1420;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1421;BA.debugLine="tiempo_actual=tiempo_actual*600";
_tiempo_actual = (int) (_tiempo_actual*600);
 //BA.debugLineNum = 1422;BA.debugLine="Log(\"Tiempo Actual minutos convertir decimas: \"&ti";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1425;BA.debugLine="Log(\"Tiempo minimo: \"&tiempo_minimo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo minimo: "+BA.NumberToString(_tiempo_minimo));
 //BA.debugLineNum = 1428;BA.debugLine="If etTiempo.Text=0 Then";
if ((mostCurrent._ettiempo.getText()).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1429;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 }else {
 //BA.debugLineNum = 1434;BA.debugLine="If tiempo_actual=tiempo_minimo Then";
if (_tiempo_actual==_tiempo_minimo) { 
 //BA.debugLineNum = 1436;BA.debugLine="Log(\"tiempo =\")";
anywheresoftware.b4a.keywords.Common.Log("tiempo =");
 //BA.debugLineNum = 1437;BA.debugLine="ret=mirar_tiempo_servo(servo,tiempo_actual)";
_ret = _mirar_tiempo_servo((int)(Double.parseDouble(mostCurrent._servo)),_tiempo_actual);
 //BA.debugLineNum = 1438;BA.debugLine="If ret=True Then";
if (_ret==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1439;BA.debugLine="grabar";
_grabar();
 //BA.debugLineNum = 1440;BA.debugLine="ret=False";
_ret = anywheresoftware.b4a.keywords.Common.False;
 };
 };
 //BA.debugLineNum = 1446;BA.debugLine="If tiempo_actual>tiempo_minimo Then";
if (_tiempo_actual>_tiempo_minimo) { 
 //BA.debugLineNum = 1448;BA.debugLine="tiempo=etTiempo.Text";
mostCurrent._tiempo = mostCurrent._ettiempo.getText();
 //BA.debugLineNum = 1449;BA.debugLine="tiempo_minimo=tiempo_actual";
_tiempo_minimo = _tiempo_actual;
 //BA.debugLineNum = 1450;BA.debugLine="Log(\"INT de tiempo:  \" & tiempo_minimo)";
anywheresoftware.b4a.keywords.Common.Log("INT de tiempo:  "+BA.NumberToString(_tiempo_minimo));
 //BA.debugLineNum = 1451;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1452;BA.debugLine="grabar";
_grabar();
 };
 //BA.debugLineNum = 1455;BA.debugLine="If tiempo_actual<tiempo_minimo Then";
if (_tiempo_actual<_tiempo_minimo) { 
 //BA.debugLineNum = 1457;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1458;BA.debugLine="Msgbox(\"Tiempo menor que el anterior\",\"ERROR\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Tiempo menor que el anterior","ERROR",mostCurrent.activityBA);
 };
 };
 } 
       catch (Exception e1053) {
			processBA.setLastException(e1053); };
 //BA.debugLineNum = 1470;BA.debugLine="End Sub";
return "";
}
public static String  _ettiempo_2() throws Exception{
boolean _ret = false;
String _str = "";
int _aux_tiempo = 0;
 //BA.debugLineNum = 1472;BA.debugLine="Sub etTiempo_2";
 //BA.debugLineNum = 1473;BA.debugLine="Try";
try { //BA.debugLineNum = 1474;BA.debugLine="ex=True";
_ex = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1475;BA.debugLine="Dim ret As Boolean";
_ret = false;
 //BA.debugLineNum = 1476;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 1477;BA.debugLine="Dim aux_tiempo As  Int";
_aux_tiempo = 0;
 //BA.debugLineNum = 1486;BA.debugLine="Log(\"tipo tiempo Anterior modificaar \"&TIPO_ANTE_L";
anywheresoftware.b4a.keywords.Common.Log("tipo tiempo Anterior modificaar "+mostCurrent._tipo_ante_lv);
 //BA.debugLineNum = 1489;BA.debugLine="Log(\"tipo tiempo Posterior modificaar \"&tIPO_POST_";
anywheresoftware.b4a.keywords.Common.Log("tipo tiempo Posterior modificaar "+mostCurrent._tipo_post_lv);
 //BA.debugLineNum = 1492;BA.debugLine="tiempo_actual=etTiempo.Text";
_tiempo_actual = (int)(Double.parseDouble(mostCurrent._ettiempo.getText()));
 //BA.debugLineNum = 1493;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1494;BA.debugLine="tiempo_actual=tiempo_actual";
_tiempo_actual = _tiempo_actual;
 //BA.debugLineNum = 1495;BA.debugLine="Log(\"Tiempo Actual decimas convertir decimas: \"&ti";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual decimas convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1498;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1499;BA.debugLine="tiempo_actual=tiempo_actual*10";
_tiempo_actual = (int) (_tiempo_actual*10);
 //BA.debugLineNum = 1500;BA.debugLine="Log(\"Tiempo Actual segundos convertir decimas: \"&t";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1502;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1503;BA.debugLine="tiempo_actual=tiempo_actual*600";
_tiempo_actual = (int) (_tiempo_actual*600);
 //BA.debugLineNum = 1504;BA.debugLine="Log(\"Tiempo Actual minutos convertir decimas: \"&ti";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos convertir decimas: "+BA.NumberToString(_tiempo_actual));
 };
 //BA.debugLineNum = 1509;BA.debugLine="If TIPO_ANTE_LV=0 Then";
if ((mostCurrent._tipo_ante_lv).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1510;BA.debugLine="tiempo_ante_lv=tiempo_ante_lv";
mostCurrent._tiempo_ante_lv = mostCurrent._tiempo_ante_lv;
 //BA.debugLineNum = 1511;BA.debugLine="Log(\"Tiempo ant decimas convertir decimas: \"&tiemp";
anywheresoftware.b4a.keywords.Common.Log("Tiempo ant decimas convertir decimas: "+mostCurrent._tiempo_ante_lv);
 //BA.debugLineNum = 1512;BA.debugLine="flag_multi=True";
_flag_multi = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1515;BA.debugLine="If TIPO_ANTE_LV=1 Then";
if ((mostCurrent._tipo_ante_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1516;BA.debugLine="tiempo_ante_lv=tiempo_ante_lv*10";
mostCurrent._tiempo_ante_lv = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo_ante_lv))*10);
 //BA.debugLineNum = 1517;BA.debugLine="Log(\"Tiempo ante segundos convertir decimas: \"&tie";
anywheresoftware.b4a.keywords.Common.Log("Tiempo ante segundos convertir decimas: "+mostCurrent._tiempo_ante_lv);
 };
 //BA.debugLineNum = 1519;BA.debugLine="If TIPO_ANTE_LV=2 Then";
if ((mostCurrent._tipo_ante_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1520;BA.debugLine="tiempo_ante_lv=tiempo_ante_lv*600";
mostCurrent._tiempo_ante_lv = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo_ante_lv))*600);
 //BA.debugLineNum = 1521;BA.debugLine="Log(\"Tiempo ante minutos convertir decimas: \"&tiem";
anywheresoftware.b4a.keywords.Common.Log("Tiempo ante minutos convertir decimas: "+mostCurrent._tiempo_ante_lv);
 //BA.debugLineNum = 1522;BA.debugLine="flag_multi=True";
_flag_multi = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1526;BA.debugLine="If tIPO_POST_LV=0 Then";
if ((mostCurrent._tipo_post_lv).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1527;BA.debugLine="tiempo_post_lv=tiempo_post_lv";
mostCurrent._tiempo_post_lv = mostCurrent._tiempo_post_lv;
 //BA.debugLineNum = 1528;BA.debugLine="Log(\"Tiempo post decimas convertir decimas: \"&tiem";
anywheresoftware.b4a.keywords.Common.Log("Tiempo post decimas convertir decimas: "+mostCurrent._tiempo_post_lv);
 //BA.debugLineNum = 1529;BA.debugLine="flag_multi=True";
_flag_multi = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1532;BA.debugLine="If tIPO_POST_LV=1 Then";
if ((mostCurrent._tipo_post_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1533;BA.debugLine="tiempo_post_lv=tiempo_post_lv*10";
mostCurrent._tiempo_post_lv = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo_post_lv))*10);
 //BA.debugLineNum = 1534;BA.debugLine="Log(\"Tiempo post segundos convertir decimas: \"&tie";
anywheresoftware.b4a.keywords.Common.Log("Tiempo post segundos convertir decimas: "+mostCurrent._tiempo_post_lv);
 };
 //BA.debugLineNum = 1536;BA.debugLine="If tIPO_POST_LV=2 Then";
if ((mostCurrent._tipo_post_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1537;BA.debugLine="tiempo_post_lv=tiempo_post_lv*600";
mostCurrent._tiempo_post_lv = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo_post_lv))*600);
 //BA.debugLineNum = 1538;BA.debugLine="Log(\"Tiempo post minutos convertir decimas: \"&tiem";
anywheresoftware.b4a.keywords.Common.Log("Tiempo post minutos convertir decimas: "+mostCurrent._tiempo_post_lv);
 };
 //BA.debugLineNum = 1542;BA.debugLine="If tiempo_actual=tiempo_ante_lv Then";
if (_tiempo_actual==(double)(Double.parseDouble(mostCurrent._tiempo_ante_lv))) { 
 //BA.debugLineNum = 1543;BA.debugLine="Log(\"tiempo =\")";
anywheresoftware.b4a.keywords.Common.Log("tiempo =");
 //BA.debugLineNum = 1544;BA.debugLine="ret=mirar_tiempo_servo(servo,tiempo_actual)";
_ret = _mirar_tiempo_servo((int)(Double.parseDouble(mostCurrent._servo)),_tiempo_actual);
 //BA.debugLineNum = 1545;BA.debugLine="Log(\"returnt mismo tiempo servo \"& ret)";
anywheresoftware.b4a.keywords.Common.Log("returnt mismo tiempo servo "+BA.ObjectToString(_ret));
 //BA.debugLineNum = 1546;BA.debugLine="If ret=True Then";
if (_ret==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1547;BA.debugLine="mod_datos_button2";
_mod_datos_button2();
 //BA.debugLineNum = 1548;BA.debugLine="ret=False";
_ret = anywheresoftware.b4a.keywords.Common.False;
 };
 }else if(_tiempo_actual==(double)(Double.parseDouble(mostCurrent._tiempo_post_lv))) { 
 //BA.debugLineNum = 1551;BA.debugLine="Log(\"tiempo =\")";
anywheresoftware.b4a.keywords.Common.Log("tiempo =");
 //BA.debugLineNum = 1552;BA.debugLine="ret=mirar_tiempo_servo(servo,tiempo_actual)";
_ret = _mirar_tiempo_servo((int)(Double.parseDouble(mostCurrent._servo)),_tiempo_actual);
 //BA.debugLineNum = 1553;BA.debugLine="Log(\"returnt mismo tiempo servo \"& ret)";
anywheresoftware.b4a.keywords.Common.Log("returnt mismo tiempo servo "+BA.ObjectToString(_ret));
 //BA.debugLineNum = 1554;BA.debugLine="If ret=True Then";
if (_ret==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1555;BA.debugLine="mod_datos_button2";
_mod_datos_button2();
 //BA.debugLineNum = 1556;BA.debugLine="ret=False";
_ret = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1558;BA.debugLine="ex=False";
_ex = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1562;BA.debugLine="If ex=True Then";
if (_ex==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1564;BA.debugLine="If tiempo_actual>tiempo_ante_lv Then";
if (_tiempo_actual>(double)(Double.parseDouble(mostCurrent._tiempo_ante_lv))) { 
 //BA.debugLineNum = 1566;BA.debugLine="If tiempo_actual<tiempo_post_lv Then";
if (_tiempo_actual<(double)(Double.parseDouble(mostCurrent._tiempo_post_lv))) { 
 //BA.debugLineNum = 1567;BA.debugLine="tiempo=tiempo_actual";
mostCurrent._tiempo = BA.NumberToString(_tiempo_actual);
 //BA.debugLineNum = 1573;BA.debugLine="mod_datos_button2";
_mod_datos_button2();
 }else {
 //BA.debugLineNum = 1575;BA.debugLine="Msgbox(\"Tiempo actual mayor que el posterior!";
anywheresoftware.b4a.keywords.Common.Msgbox("Tiempo actual mayor que el posterior!!!!!","ERROR",mostCurrent.activityBA);
 //BA.debugLineNum = 1576;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1577;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1578;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1579;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1580;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1581;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1582;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1583;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1584;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1585;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1586;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1587;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1588;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 }else {
 //BA.debugLineNum = 1592;BA.debugLine="Msgbox(\"Tiempo actual menor que el anterior\",\"ER";
anywheresoftware.b4a.keywords.Common.Msgbox("Tiempo actual menor que el anterior","ERROR",mostCurrent.activityBA);
 //BA.debugLineNum = 1593;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1594;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1595;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1596;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1597;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1598;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1599;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1600;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1601;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1602;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1604;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1605;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1606;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 };
 } 
       catch (Exception e1160) {
			processBA.setLastException(e1160); };
 //BA.debugLineNum = 1615;BA.debugLine="End Sub";
return "";
}
public static String  _ettiempo_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 1380;BA.debugLine="Sub etTiempo_TextChanged (Old As String, New As St";
 //BA.debugLineNum = 1381;BA.debugLine="Try";
try { //BA.debugLineNum = 1382;BA.debugLine="If New>0 Then";
if ((double)(Double.parseDouble(_new))>0) { 
 //BA.debugLineNum = 1383;BA.debugLine="If New<=999 Then";
if ((double)(Double.parseDouble(_new))<=999) { 
 //BA.debugLineNum = 1384;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 1386;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1387;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 };
 };
 } 
       catch (Exception e1007) {
			processBA.setLastException(e1007); };
 //BA.debugLineNum = 1392;BA.debugLine="End Sub";
return "";
}
public static String  _ficheronuevo() throws Exception{
 //BA.debugLineNum = 236;BA.debugLine="Sub ficheronuevo";
 //BA.debugLineNum = 237;BA.debugLine="Try";
try { //BA.debugLineNum = 238;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 239;BA.debugLine="spconfig.Enabled=True";
mostCurrent._spconfig.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 240;BA.debugLine="If strspinner=\"PULSE AQUI\" Then";
if ((_strspinner).equals("PULSE AQUI")) { 
 //BA.debugLineNum = 242;BA.debugLine="spconfig.SelectedIndex=0";
mostCurrent._spconfig.setSelectedIndex((int) (0));
 };
 //BA.debugLineNum = 245;BA.debugLine="lvDatos.CLEAR";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 246;BA.debugLine="lbModelo.Text=strfichero";
mostCurrent._lbmodelo.setText((Object)(_strfichero));
 //BA.debugLineNum = 247;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 248;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 249;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 250;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 251;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 252;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 253;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 254;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 255;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 256;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 257;BA.debugLine="Panel2.Visible=False";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 258;BA.debugLine="Label5.Visible=False";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 259;BA.debugLine="lbNumeroServo.Visible=False";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 260;BA.debugLine="lbservotxt.Visible=False";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 261;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 //BA.debugLineNum = 262;BA.debugLine="lbNumeroServo.Text=\"C.I.ALA\"";
mostCurrent._lbnumeroservo.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 263;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 264;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 265;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 266;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 267;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 268;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 269;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 270;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 271;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 273;BA.debugLine="tiempo_actual=0";
_tiempo_actual = (int) (0);
 } 
       catch (Exception e210) {
			processBA.setLastException(e210); };
 //BA.debugLineNum = 278;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Dim dlgFileExpl As ClsExplorer";
mostCurrent._dlgfileexpl = new com.delozoya.Consola.Completa.clsexplorer();
 //BA.debugLineNum = 25;BA.debugLine="Private btOK As Button";
mostCurrent._btok = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private etFichero As EditText";
mostCurrent._etfichero = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private etGiro As EditText";
mostCurrent._etgiro = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private etTiempo As EditText";
mostCurrent._ettiempo = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private lvDatos As ListView";
mostCurrent._lvdatos = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private spconfig As Spinner";
mostCurrent._spconfig = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private spServo As Spinner";
mostCurrent._spservo = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Dim timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 35;BA.debugLine="Dim ex As Boolean";
_ex = false;
 //BA.debugLineNum = 37;BA.debugLine="Private dial As InputDialog";
mostCurrent._dial = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 39;BA.debugLine="Private spTiempo As Spinner";
mostCurrent._sptiempo = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private Label8 As Label";
mostCurrent._label8 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private Label9 As Label";
mostCurrent._label9 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim NOMBRE_FICHERO As String";
mostCurrent._nombre_fichero = "";
 //BA.debugLineNum = 43;BA.debugLine="Dim config As String";
mostCurrent._config = "";
 //BA.debugLineNum = 45;BA.debugLine="Dim di As CustomDialog2";
mostCurrent._di = new anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2();
 //BA.debugLineNum = 49;BA.debugLine="Dim contador As Int";
_contador = 0;
 //BA.debugLineNum = 50;BA.debugLine="Dim buffer_tx(512) As Byte";
_buffer_tx = new byte[(int) (512)];
;
 //BA.debugLineNum = 52;BA.debugLine="Dim valor_d As Byte";
_valor_d = (byte)0;
 //BA.debugLineNum = 53;BA.debugLine="Dim first_time As Boolean";
_first_time = false;
 //BA.debugLineNum = 54;BA.debugLine="Private btEscribirMemoria As Button";
mostCurrent._btescribirmemoria = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private lbrx As Label";
mostCurrent._lbrx = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private bt124 As Button";
mostCurrent._bt124 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private bt1 As Button";
mostCurrent._bt1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private btascii49 As Button";
mostCurrent._btascii49 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Dim flag_d As Boolean";
_flag_d = false;
 //BA.debugLineNum = 60;BA.debugLine="Private btTestAvr As Button";
mostCurrent._bttestavr = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Dim x As Int";
_x = 0;
 //BA.debugLineNum = 62;BA.debugLine="Dim flag_rx As Boolean";
_flag_rx = false;
 //BA.debugLineNum = 63;BA.debugLine="Dim contador As Int";
_contador = 0;
 //BA.debugLineNum = 65;BA.debugLine="Private btLeer As Button";
mostCurrent._btleer = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Dim listaconfiguracion As List";
mostCurrent._listaconfiguracion = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 68;BA.debugLine="Dim lista1 As List";
mostCurrent._lista1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 69;BA.debugLine="Dim contador_lineas As Int";
_contador_lineas = 0;
 //BA.debugLineNum = 70;BA.debugLine="Dim servo As String";
mostCurrent._servo = "";
 //BA.debugLineNum = 71;BA.debugLine="Dim giro As String";
mostCurrent._giro = "";
 //BA.debugLineNum = 72;BA.debugLine="Dim tiempo As String";
mostCurrent._tiempo = "";
 //BA.debugLineNum = 73;BA.debugLine="Dim tipo_tiempo As String";
mostCurrent._tipo_tiempo = "";
 //BA.debugLineNum = 75;BA.debugLine="Dim servo_lv As String";
mostCurrent._servo_lv = "";
 //BA.debugLineNum = 76;BA.debugLine="Dim tiempo_lv As String";
mostCurrent._tiempo_lv = "";
 //BA.debugLineNum = 77;BA.debugLine="Dim tipo_tiempo_lv As String";
mostCurrent._tipo_tiempo_lv = "";
 //BA.debugLineNum = 78;BA.debugLine="Dim giro_lv As String";
mostCurrent._giro_lv = "";
 //BA.debugLineNum = 79;BA.debugLine="Dim directorio As String";
mostCurrent._directorio = "";
 //BA.debugLineNum = 81;BA.debugLine="Dim tiempo_aux As String";
mostCurrent._tiempo_aux = "";
 //BA.debugLineNum = 82;BA.debugLine="Dim tipo_tiempo_aux As String";
mostCurrent._tipo_tiempo_aux = "";
 //BA.debugLineNum = 83;BA.debugLine="Dim tipo_tiempo_aux1 As String";
mostCurrent._tipo_tiempo_aux1 = "";
 //BA.debugLineNum = 85;BA.debugLine="Dim giro_aux As String";
mostCurrent._giro_aux = "";
 //BA.debugLineNum = 86;BA.debugLine="Dim servo_aux As String";
mostCurrent._servo_aux = "";
 //BA.debugLineNum = 87;BA.debugLine="Dim servo_aux_nombre As String";
mostCurrent._servo_aux_nombre = "";
 //BA.debugLineNum = 89;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 90;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 91;BA.debugLine="Private lbNumeroServo As Label";
mostCurrent._lbnumeroservo = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 92;BA.debugLine="Private lbModelo As Label";
mostCurrent._lbmodelo = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 93;BA.debugLine="Private label10 As Label";
mostCurrent._label10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 94;BA.debugLine="Dim posicion_mod As String";
mostCurrent._posicion_mod = "";
 //BA.debugLineNum = 96;BA.debugLine="Dim tiempo_ante_lv As String";
mostCurrent._tiempo_ante_lv = "";
 //BA.debugLineNum = 97;BA.debugLine="Dim tiempo_post_lv As String";
mostCurrent._tiempo_post_lv = "";
 //BA.debugLineNum = 98;BA.debugLine="Dim servo_ante_lv As String";
mostCurrent._servo_ante_lv = "";
 //BA.debugLineNum = 99;BA.debugLine="Dim servo_post_lv As String";
mostCurrent._servo_post_lv = "";
 //BA.debugLineNum = 100;BA.debugLine="Dim TIPO_ANTE_LV As String";
mostCurrent._tipo_ante_lv = "";
 //BA.debugLineNum = 101;BA.debugLine="Dim tIPO_POST_LV As String";
mostCurrent._tipo_post_lv = "";
 //BA.debugLineNum = 102;BA.debugLine="Dim flag_multi As Boolean";
_flag_multi = false;
 //BA.debugLineNum = 106;BA.debugLine="Dim flag_button2 As Boolean=False";
_flag_button2 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 108;BA.debugLine="Private Button2 As Button";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 111;BA.debugLine="Dim valor_inicial As Int";
_valor_inicial = 0;
 //BA.debugLineNum = 112;BA.debugLine="Dim valor_final As Int";
_valor_final = 0;
 //BA.debugLineNum = 113;BA.debugLine="Dim numero_suma As Int";
_numero_suma = 0;
 //BA.debugLineNum = 114;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 115;BA.debugLine="Dim BC As ColorDrawable";
mostCurrent._bc = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 116;BA.debugLine="Dim BC1 As ColorDrawable";
mostCurrent._bc1 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 117;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 118;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 119;BA.debugLine="Private Panel3 As Panel";
mostCurrent._panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 120;BA.debugLine="Private Panel4 As Panel";
mostCurrent._panel4 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 121;BA.debugLine="Dim spconfig1 As Spinner";
mostCurrent._spconfig1 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 122;BA.debugLine="Private btComenzar As Button";
mostCurrent._btcomenzar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 123;BA.debugLine="Private btSalir As Button";
mostCurrent._btsalir = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 125;BA.debugLine="Dim ime1 As IME";
mostCurrent._ime1 = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 126;BA.debugLine="Private btgrabar As Button";
mostCurrent._btgrabar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 127;BA.debugLine="Dim tiempo_minimo As Int";
_tiempo_minimo = 0;
 //BA.debugLineNum = 128;BA.debugLine="Dim tiempo_actual As Int";
_tiempo_actual = 0;
 //BA.debugLineNum = 129;BA.debugLine="Dim tiempo_repite As Boolean";
_tiempo_repite = false;
 //BA.debugLineNum = 130;BA.debugLine="Private Label5 As Label";
mostCurrent._label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 131;BA.debugLine="Dim tim As Timer";
mostCurrent._tim = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 132;BA.debugLine="Private lbservotxt As Label";
mostCurrent._lbservotxt = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 133;BA.debugLine="Private btLeer As Button";
mostCurrent._btleer = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 134;BA.debugLine="Private cbled As CheckBox";
mostCurrent._cbled = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 136;BA.debugLine="Dim nombre_leido As String";
mostCurrent._nombre_leido = "";
 //BA.debugLineNum = 139;BA.debugLine="Dim flag_lectura As Boolean=False";
_flag_lectura = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 140;BA.debugLine="Dim t As Int=0";
_t = (int) (0);
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return "";
}
public static String  _grabar() throws Exception{
String _servo_nombre = "";
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _i = 0;
 //BA.debugLineNum = 2015;BA.debugLine="Sub grabar";
 //BA.debugLineNum = 2016;BA.debugLine="Dim servo_nombre As String";
_servo_nombre = "";
 //BA.debugLineNum = 2017;BA.debugLine="Try";
try { //BA.debugLineNum = 2018;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 2019;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 2020;BA.debugLine="If contador_lineas<254 Then";
if (_contador_lineas<254) { 
 //BA.debugLineNum = 2021;BA.debugLine="If contador_lineas>=12 Then";
if (_contador_lineas>=12) { 
 //BA.debugLineNum = 2024;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 2025;BA.debugLine="aux_valor_tiempo=\"00\"&tiempo";
_aux_valor_tiempo = "00"+mostCurrent._tiempo;
 //BA.debugLineNum = 2026;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 2030;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 2031;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 2032;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 2033;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 2038;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 2040;BA.debugLine="aux_valor_tiempo=tiempo";
_aux_valor_tiempo = mostCurrent._tiempo;
 //BA.debugLineNum = 2041;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 2046;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 2048;BA.debugLine="lista1.Add(\"0\")";
mostCurrent._lista1.Add((Object)("0"));
 };
 //BA.debugLineNum = 2050;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 2052;BA.debugLine="lista1.Add(\"1\")";
mostCurrent._lista1.Add((Object)("1"));
 };
 //BA.debugLineNum = 2054;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 2056;BA.debugLine="lista1.Add(\"2\")";
mostCurrent._lista1.Add((Object)("2"));
 };
 //BA.debugLineNum = 2059;BA.debugLine="lista1.Add(servo)";
mostCurrent._lista1.Add((Object)(mostCurrent._servo));
 //BA.debugLineNum = 2060;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2061;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 2062;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2066;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2067;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2068;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2069;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2074;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2075;BA.debugLine="aux_valor_giro=giro";
_aux_valor_giro = mostCurrent._giro;
 //BA.debugLineNum = 2077;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2081;BA.debugLine="log_lista";
_log_lista();
 //BA.debugLineNum = 2082;BA.debugLine="contador_lineas=contador_lineas+4";
_contador_lineas = (int) (_contador_lineas+4);
 //BA.debugLineNum = 2083;BA.debugLine="Log(\"numero lineas  \"&(contador_lineas-2))";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString((_contador_lineas-2)));
 };
 }else {
 //BA.debugLineNum = 2091;BA.debugLine="ToastMessageShow(\"Maximo lineas\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Maximo lineas",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2092;BA.debugLine="Log(\"numero lineas  \"&(contador_lineas-2))";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString((_contador_lineas-2)));
 //BA.debugLineNum = 2093;BA.debugLine="Log(\"------------------------------\")";
anywheresoftware.b4a.keywords.Common.Log("------------------------------");
 //BA.debugLineNum = 2094;BA.debugLine="Log(\"------------------------------\")";
anywheresoftware.b4a.keywords.Common.Log("------------------------------");
 //BA.debugLineNum = 2096;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step54 = 1;
final int limit54 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0) ; (step54 > 0 && _i <= limit54) || (step54 < 0 && _i >= limit54); _i = ((int)(0 + _i + step54)) ) {
 //BA.debugLineNum = 2098;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 };
 //BA.debugLineNum = 2102;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 2106;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 2107;BA.debugLine="servo_nombre=\"C.INCI.ALAS 1\"";
_servo_nombre = "C.INCI.ALAS 1";
 };
 //BA.debugLineNum = 2109;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 2110;BA.debugLine="servo_nombre=\"CORTE.MOTOR 2\"";
_servo_nombre = "CORTE.MOTOR 2";
 };
 //BA.debugLineNum = 2112;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 2113;BA.debugLine="servo_nombre=\"INC.STABILO 3\"";
_servo_nombre = "INC.STABILO 3";
 };
 //BA.debugLineNum = 2115;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 2116;BA.debugLine="servo_nombre=\"TIMON DIREC 4\"";
_servo_nombre = "TIMON DIREC 4";
 };
 //BA.debugLineNum = 2118;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 2119;BA.debugLine="servo_nombre=\"FLAP-FOLDER 5\"";
_servo_nombre = "FLAP-FOLDER 5";
 };
 //BA.debugLineNum = 2125;BA.debugLine="lvDatos.AddSingleLine(servo_nombre&\"   Giro: \"&au";
mostCurrent._lvdatos.AddSingleLine(_servo_nombre+"   Giro: "+_aux_valor_giro+"    Tiempo: "+_aux_valor_tiempo+"  Tipo tiempo: "+mostCurrent._tipo_tiempo);
 //BA.debugLineNum = 2126;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 2130;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 2131;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 2132;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 2133;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 2134;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 2135;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 2136;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 2137;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2138;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2139;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2140;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2141;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e1516) {
			processBA.setLastException(e1516); };
 //BA.debugLineNum = 2146;BA.debugLine="End Sub";
return "";
}
public static String  _initpanel(anywheresoftware.b4a.objects.PanelWrapper _pnl,float _borderwidth,int _fillcolor,int _bordercolor) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rec = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas1 = null;
float _borderwidth_2 = 0f;
 //BA.debugLineNum = 2559;BA.debugLine="Sub InitPanel(pnl As Panel,BorderWidth As Float, F";
 //BA.debugLineNum = 2560;BA.debugLine="Dim Rec As Rect";
_rec = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 2561;BA.debugLine="Dim Canvas1 As Canvas";
_canvas1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 2562;BA.debugLine="Dim BorderWidth_2 As Float";
_borderwidth_2 = 0f;
 //BA.debugLineNum = 2564;BA.debugLine="BorderWidth_2=BorderWidth/2";
_borderwidth_2 = (float) (_borderwidth/(double)2);
 //BA.debugLineNum = 2565;BA.debugLine="Rec.Initialize(BorderWidth_2,BorderWidth_2,pnl.Wi";
_rec.Initialize((int) (_borderwidth_2),(int) (_borderwidth_2),(int) (_pnl.getWidth()-_borderwidth_2),(int) (_pnl.getHeight()-_borderwidth_2));
 //BA.debugLineNum = 2566;BA.debugLine="Canvas1.Initialize(pnl)";
_canvas1.Initialize((android.view.View)(_pnl.getObject()));
 //BA.debugLineNum = 2567;BA.debugLine="Canvas1.DrawRect(Rec,FillColor,True,FillColor)";
_canvas1.DrawRect((android.graphics.Rect)(_rec.getObject()),_fillcolor,anywheresoftware.b4a.keywords.Common.True,(float) (_fillcolor));
 //BA.debugLineNum = 2568;BA.debugLine="Canvas1.DrawRect(Rec,BorderColor,False,BorderWidt";
_canvas1.DrawRect((android.graphics.Rect)(_rec.getObject()),_bordercolor,anywheresoftware.b4a.keywords.Common.False,_borderwidth);
 //BA.debugLineNum = 2569;BA.debugLine="End Sub";
return "";
}
public static String  _lbmodelo_longclick() throws Exception{
 //BA.debugLineNum = 2423;BA.debugLine="Sub lbModelo_LongClick";
 //BA.debugLineNum = 2425;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 2427;BA.debugLine="dlgFileExpl.Initialize(Activity, \"/mnt/sdcard/Cons";
mostCurrent._dlgfileexpl._initialize(mostCurrent.activityBA,mostCurrent._activity,"/mnt/sdcard/Consola/F1C","",anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False,"OK");
 //BA.debugLineNum = 2429;BA.debugLine="dlgFileExpl.BorderColor = Colors.RGB(128, 128, 12";
mostCurrent._dlgfileexpl._bordercolor = anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (128),(int) (128),(int) (128));
 //BA.debugLineNum = 2430;BA.debugLine="dlgFileExpl.FolderTextColor = Colors.Black";
mostCurrent._dlgfileexpl._foldertextcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
 //BA.debugLineNum = 2431;BA.debugLine="dlgFileExpl.FileTextColor1 = Colors.black";
mostCurrent._dlgfileexpl._filetextcolor1 = anywheresoftware.b4a.keywords.Common.Colors.Black;
 //BA.debugLineNum = 2432;BA.debugLine="dlgFileExpl.Explorer";
mostCurrent._dlgfileexpl._explorer();
 //BA.debugLineNum = 2433;BA.debugLine="If Not(dlgFileExpl.Selection.Canceled Or dlgFileE";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._dlgfileexpl._selection.Canceled || (mostCurrent._dlgfileexpl._selection.ChosenPath).equals(""))) { 
 };
 //BA.debugLineNum = 2438;BA.debugLine="End Sub";
return "";
}
public static String  _leer_fichero() throws Exception{
int _i = 0;
String _aux = "";
byte[] _b = null;
 //BA.debugLineNum = 2852;BA.debugLine="Sub Leer_fichero";
 //BA.debugLineNum = 2857;BA.debugLine="For i=0 To lista1.Size-1";
{
final int step1 = 1;
final int limit1 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0) ; (step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1); _i = ((int)(0 + _i + step1)) ) {
 //BA.debugLineNum = 2859;BA.debugLine="buffer_tx(i)=lista1.Get(i)";
_buffer_tx[_i] = (byte)(BA.ObjectToNumber(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 2862;BA.debugLine="For i=lista1.Size To 255";
{
final int step4 = 1;
final int limit4 = (int) (255);
for (_i = mostCurrent._lista1.getSize() ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 2863;BA.debugLine="buffer_tx(i)=0";
_buffer_tx[_i] = (byte) (0);
 }
};
 //BA.debugLineNum = 2867;BA.debugLine="buffer_tx(222)=3";
_buffer_tx[(int) (222)] = (byte) (3);
 //BA.debugLineNum = 2868;BA.debugLine="buffer_tx(223)=listaconfiguracion.Get(0)";
_buffer_tx[(int) (223)] = (byte)(BA.ObjectToNumber(mostCurrent._listaconfiguracion.Get((int) (0))));
 //BA.debugLineNum = 2869;BA.debugLine="Dim aux As String";
_aux = "";
 //BA.debugLineNum = 2870;BA.debugLine="Dim b() As Byte";
_b = new byte[(int) (0)];
;
 //BA.debugLineNum = 2871;BA.debugLine="aux=listaconfiguracion.Get(1)";
_aux = BA.ObjectToString(mostCurrent._listaconfiguracion.Get((int) (1)));
 //BA.debugLineNum = 2872;BA.debugLine="b= aux.GetBytes(\"UTF-8\")";
_b = _aux.getBytes("UTF-8");
 //BA.debugLineNum = 2873;BA.debugLine="For i=0 To b.Length-1";
{
final int step13 = 1;
final int limit13 = (int) (_b.length-1);
for (_i = (int) (0) ; (step13 > 0 && _i <= limit13) || (step13 < 0 && _i >= limit13); _i = ((int)(0 + _i + step13)) ) {
 //BA.debugLineNum = 2874;BA.debugLine="buffer_tx(i+224)=b(i)";
_buffer_tx[(int) (_i+224)] = _b[_i];
 }
};
 //BA.debugLineNum = 2878;BA.debugLine="For i=0 To 255";
{
final int step16 = 1;
final int limit16 = (int) (255);
for (_i = (int) (0) ; (step16 > 0 && _i <= limit16) || (step16 < 0 && _i >= limit16); _i = ((int)(0 + _i + step16)) ) {
 //BA.debugLineNum = 2879;BA.debugLine="Log(buffer_tx(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_buffer_tx[_i]));
 }
};
 //BA.debugLineNum = 2882;BA.debugLine="Log( \"FICHERO DE DATOS LEIDO\")";
anywheresoftware.b4a.keywords.Common.Log("FICHERO DE DATOS LEIDO");
 //BA.debugLineNum = 2884;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
com.delozoya.Consola.Completa.customlistview _lv = null;
 //BA.debugLineNum = 2552;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 2553;BA.debugLine="Dim lv As CustomListView";
_lv = new com.delozoya.Consola.Completa.customlistview();
 //BA.debugLineNum = 2554;BA.debugLine="ListView1.SetSelection(Position)";
mostCurrent._listview1.SetSelection(_position);
 //BA.debugLineNum = 2555;BA.debugLine="lvDatos.SetSelection(Position)";
mostCurrent._lvdatos.SetSelection(_position);
 //BA.debugLineNum = 2557;BA.debugLine="End Sub";
return "";
}
public static String  _log_lista() throws Exception{
int _i = 0;
 //BA.debugLineNum = 2359;BA.debugLine="Sub log_lista";
 //BA.debugLineNum = 2360;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step1 = 1;
final int limit1 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0) ; (step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1); _i = ((int)(0 + _i + step1)) ) {
 //BA.debugLineNum = 2362;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 2367;BA.debugLine="End Sub";
return "";
}
public static String  _lvdatos_itemclick(int _position,Object _value) throws Exception{
String _str = "";
String _lv_tipo = "";
int _lv_tipo1 = 0;
 //BA.debugLineNum = 837;BA.debugLine="Sub lvDatos_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 838;BA.debugLine="Try";
try { //BA.debugLineNum = 839;BA.debugLine="tiempo_ante_lv=0";
mostCurrent._tiempo_ante_lv = BA.NumberToString(0);
 //BA.debugLineNum = 840;BA.debugLine="Button2.Enabled=True";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 841;BA.debugLine="Button2.Visible=True";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 842;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 843;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 844;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 845;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 846;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 847;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 848;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 849;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 850;BA.debugLine="etTiempo.Enabled=True";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 851;BA.debugLine="spTiempo.Enabled=True";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 852;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 853;BA.debugLine="Dim lv_tipo As String";
_lv_tipo = "";
 //BA.debugLineNum = 854;BA.debugLine="Dim lv_tipo1  As Int";
_lv_tipo1 = 0;
 //BA.debugLineNum = 858;BA.debugLine="If Position<=4 Then";
if (_position<=4) { 
 //BA.debugLineNum = 859;BA.debugLine="Button2.Enabled=True";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 860;BA.debugLine="Button2.Visible=True";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 861;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 862;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 863;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 864;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 865;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 866;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 867;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 868;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 869;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 870;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 871;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 872;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 873;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 875;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 876;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 877;BA.debugLine="servo_lv=str.SubString2(12,13)";
mostCurrent._servo_lv = _str.substring((int) (12),(int) (13));
 //BA.debugLineNum = 878;BA.debugLine="giro_lv=str.SubString2(22,25)";
mostCurrent._giro_lv = _str.substring((int) (22),(int) (25));
 //BA.debugLineNum = 879;BA.debugLine="lbNumeroServo.text=servo_lv";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo_lv));
 //BA.debugLineNum = 881;BA.debugLine="If servo_lv=1 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 882;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 884;BA.debugLine="If servo_lv=2 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 885;BA.debugLine="lbservotxt.Text=\"MOTOR\"";
mostCurrent._lbservotxt.setText((Object)("MOTOR"));
 };
 //BA.debugLineNum = 887;BA.debugLine="If servo_lv=3 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 888;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 890;BA.debugLine="If servo_lv=4 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 891;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 893;BA.debugLine="If servo_lv=5 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 894;BA.debugLine="lbservotxt.Text=\"FLAP-FOLDER\"";
mostCurrent._lbservotxt.setText((Object)("FLAP-FOLDER"));
 };
 //BA.debugLineNum = 897;BA.debugLine="Log(\"giro_lv: \"&giro_lv)";
anywheresoftware.b4a.keywords.Common.Log("giro_lv: "+mostCurrent._giro_lv);
 //BA.debugLineNum = 898;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 899;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 902;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 903;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 904;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 909;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 911;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 919;BA.debugLine="flag_button2=True";
_flag_button2 = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 923;BA.debugLine="If Position>=5 Then";
if (_position>=5) { 
 //BA.debugLineNum = 924;BA.debugLine="If Position<=9 Then";
if (_position<=9) { 
 //BA.debugLineNum = 925;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 926;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 927;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 928;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 929;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 930;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 931;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 932;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 933;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 934;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 935;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 936;BA.debugLine="Label9.Visible=True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 937;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 939;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 940;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 941;BA.debugLine="servo_lv=str.SubString2(12,13)";
mostCurrent._servo_lv = _str.substring((int) (12),(int) (13));
 //BA.debugLineNum = 942;BA.debugLine="giro_lv=str.SubString2(22,25)";
mostCurrent._giro_lv = _str.substring((int) (22),(int) (25));
 //BA.debugLineNum = 943;BA.debugLine="lbNumeroServo.text=servo_lv";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo_lv));
 //BA.debugLineNum = 944;BA.debugLine="If servo_lv=1 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 945;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 947;BA.debugLine="If servo_lv=2 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 948;BA.debugLine="lbservotxt.Text=\"MOTOR\"";
mostCurrent._lbservotxt.setText((Object)("MOTOR"));
 };
 //BA.debugLineNum = 950;BA.debugLine="If servo_lv=3 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 951;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 953;BA.debugLine="If servo_lv=4 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 954;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 956;BA.debugLine="If servo_lv=5 Then";
if ((mostCurrent._servo_lv).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 957;BA.debugLine="lbservotxt.Text=\"FLAP-FOLDER\"";
mostCurrent._lbservotxt.setText((Object)("FLAP-FOLDER"));
 };
 //BA.debugLineNum = 960;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 961;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 964;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 965;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 966;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 971;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 973;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 980;BA.debugLine="flag_button2=True";
_flag_button2 = anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 985;BA.debugLine="If Position>=10 Then";
if (_position>=10) { 
 //BA.debugLineNum = 986;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 987;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 988;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 989;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 990;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 991;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 992;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 993;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 994;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 995;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 996;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 997;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1001;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 1002;BA.debugLine="mod_tiempo(Position)";
_mod_tiempo(_position);
 //BA.debugLineNum = 1004;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 1007;BA.debugLine="lv_tipo=str.SubString2(55,56)";
_lv_tipo = _str.substring((int) (55),(int) (56));
 //BA.debugLineNum = 1010;BA.debugLine="If lv_tipo=\"D\" Then";
if ((_lv_tipo).equals("D")) { 
 //BA.debugLineNum = 1011;BA.debugLine="lv_tipo1=1";
_lv_tipo1 = (int) (1);
 //BA.debugLineNum = 1012;BA.debugLine="tipo_tiempo=\"DECIMAS\"";
mostCurrent._tipo_tiempo = "DECIMAS";
 };
 //BA.debugLineNum = 1015;BA.debugLine="If lv_tipo=\"S\" Then";
if ((_lv_tipo).equals("S")) { 
 //BA.debugLineNum = 1016;BA.debugLine="lv_tipo1=2";
_lv_tipo1 = (int) (2);
 //BA.debugLineNum = 1017;BA.debugLine="tipo_tiempo=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo = "SEGUNDOS";
 };
 //BA.debugLineNum = 1020;BA.debugLine="If lv_tipo=\"M\" Then";
if ((_lv_tipo).equals("M")) { 
 //BA.debugLineNum = 1021;BA.debugLine="lv_tipo1=3";
_lv_tipo1 = (int) (3);
 //BA.debugLineNum = 1022;BA.debugLine="tipo_tiempo=\"MINUTOS\"";
mostCurrent._tipo_tiempo = "MINUTOS";
 };
 //BA.debugLineNum = 1025;BA.debugLine="tiempo_lv =str.SubString2(37,40)";
mostCurrent._tiempo_lv = _str.substring((int) (37),(int) (40));
 //BA.debugLineNum = 1026;BA.debugLine="etTiempo.Text=tiempo_lv";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv));
 //BA.debugLineNum = 1028;BA.debugLine="If tiempo_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))<=9) { 
 //BA.debugLineNum = 1029;BA.debugLine="etTiempo.Text=tiempo_lv.SubString(2)";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 1032;BA.debugLine="If tiempo_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))<=99) { 
 //BA.debugLineNum = 1033;BA.debugLine="If tiempo_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))>9) { 
 //BA.debugLineNum = 1034;BA.debugLine="etTiempo.Text=tiempo_lv.SubString2(1,3)";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 1039;BA.debugLine="If tiempo_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))>99) { 
 //BA.debugLineNum = 1041;BA.debugLine="etTiempo.Text=tiempo_lv";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv));
 };
 //BA.debugLineNum = 1046;BA.debugLine="servo_lv=str.SubString2(12,13)";
mostCurrent._servo_lv = _str.substring((int) (12),(int) (13));
 //BA.debugLineNum = 1047;BA.debugLine="servo=servo_lv";
mostCurrent._servo = mostCurrent._servo_lv;
 //BA.debugLineNum = 1048;BA.debugLine="lbNumeroServo.Text=servo";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo));
 //BA.debugLineNum = 1050;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1051;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 };
 //BA.debugLineNum = 1053;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1054;BA.debugLine="lbservotxt.Text=\"MOTOR\"";
mostCurrent._lbservotxt.setText((Object)("MOTOR"));
 };
 //BA.debugLineNum = 1056;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 1057;BA.debugLine="lbservotxt.Text=\"STABILO\"";
mostCurrent._lbservotxt.setText((Object)("STABILO"));
 };
 //BA.debugLineNum = 1059;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 1060;BA.debugLine="lbservotxt.Text=\"TIMON DIREC\"";
mostCurrent._lbservotxt.setText((Object)("TIMON DIREC"));
 };
 //BA.debugLineNum = 1062;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 1063;BA.debugLine="lbservotxt.Text=\"FLAP-FOLDER\"";
mostCurrent._lbservotxt.setText((Object)("FLAP-FOLDER"));
 };
 //BA.debugLineNum = 1070;BA.debugLine="spServo.SelectedIndex=servo_lv";
mostCurrent._spservo.setSelectedIndex((int)(Double.parseDouble(mostCurrent._servo_lv)));
 //BA.debugLineNum = 1071;BA.debugLine="spTiempo.SelectedIndex=lv_tipo1";
mostCurrent._sptiempo.setSelectedIndex(_lv_tipo1);
 //BA.debugLineNum = 1074;BA.debugLine="giro_lv=str.SubString2(22,25)";
mostCurrent._giro_lv = _str.substring((int) (22),(int) (25));
 //BA.debugLineNum = 1075;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 1076;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 1079;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 1080;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 1081;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 1086;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 1088;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 1092;BA.debugLine="tipo_tiempo_aux1=str.SubString2(55,56)";
mostCurrent._tipo_tiempo_aux1 = _str.substring((int) (55),(int) (56));
 };
 } 
       catch (Exception e810) {
			processBA.setLastException(e810); };
 //BA.debugLineNum = 1112;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 1276;BA.debugLine="Sub lvDatos_ItemLongClick (Position As Int, Value";
 //BA.debugLineNum = 1278;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 1279;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 1280;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 1281;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 1282;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 1283;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 1284;BA.debugLine="If Position<10 Then";
if (_position<10) { 
 }else {
 //BA.debugLineNum = 1289;BA.debugLine="lvDatos.RemoveAt(Position)";
mostCurrent._lvdatos.RemoveAt(_position);
 //BA.debugLineNum = 1293;BA.debugLine="If posicion_mod<20 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<20) { 
 //BA.debugLineNum = 1294;BA.debugLine="If posicion_mod>=10 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=10) { 
 //BA.debugLineNum = 1295;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1296;BA.debugLine="pos_valor=(pos_mod_aux*4)+10";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+10);
 //BA.debugLineNum = 1298;BA.debugLine="If posicion_mod= lvDatos.Size Then";
if ((mostCurrent._posicion_mod).equals(BA.NumberToString(mostCurrent._lvdatos.getSize()))) { 
 //BA.debugLineNum = 1299;BA.debugLine="For i=0 To 3";
{
final int step15 = 1;
final int limit15 = (int) (3);
for (_i = (int) (0) ; (step15 > 0 && _i <= limit15) || (step15 < 0 && _i >= limit15); _i = ((int)(0 + _i + step15)) ) {
 //BA.debugLineNum = 1300;BA.debugLine="lista1.RemoveAt(pos_valor-1)";
mostCurrent._lista1.RemoveAt((int) (_pos_valor-1));
 }
};
 }else {
 //BA.debugLineNum = 1304;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 1305;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 1306;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 //BA.debugLineNum = 1307;BA.debugLine="lista1.RemoveAt(pos_valor)";
mostCurrent._lista1.RemoveAt(_pos_valor);
 };
 };
 };
 //BA.debugLineNum = 1319;BA.debugLine="borrar_datos(20,30,50)";
_borrar_datos((int) (20),(int) (30),(int) (50));
 //BA.debugLineNum = 1321;BA.debugLine="borrar_datos(30,40,90)";
_borrar_datos((int) (30),(int) (40),(int) (90));
 //BA.debugLineNum = 1323;BA.debugLine="borrar_datos(40,50,130)";
_borrar_datos((int) (40),(int) (50),(int) (130));
 //BA.debugLineNum = 1325;BA.debugLine="borrar_datos(50,60,170)";
_borrar_datos((int) (50),(int) (60),(int) (170));
 //BA.debugLineNum = 1328;BA.debugLine="Dim test As Int";
_test = 0;
 //BA.debugLineNum = 1330;BA.debugLine="For i=0 To lista1.Size-1";
{
final int step31 = 1;
final int limit31 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0) ; (step31 > 0 && _i <= limit31) || (step31 < 0 && _i >= limit31); _i = ((int)(0 + _i + step31)) ) {
 //BA.debugLineNum = 1332;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 1335;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 1336;BA.debugLine="If Position=10 Then";
if (_position==10) { 
 //BA.debugLineNum = 1337;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 }else {
 //BA.debugLineNum = 1339;BA.debugLine="tiempo_minimo=lista1.Get(lista1.Size-4)";
_tiempo_minimo = (int)(BA.ObjectToNumber(mostCurrent._lista1.Get((int) (mostCurrent._lista1.getSize()-4))));
 //BA.debugLineNum = 1341;BA.debugLine="test=lista1.Get(lista1.Size-3)";
_test = (int)(BA.ObjectToNumber(mostCurrent._lista1.Get((int) (mostCurrent._lista1.getSize()-3))));
 //BA.debugLineNum = 1344;BA.debugLine="If test=0 Then";
if (_test==0) { 
 //BA.debugLineNum = 1345;BA.debugLine="tiempo_minimo=tiempo_minimo";
_tiempo_minimo = _tiempo_minimo;
 };
 //BA.debugLineNum = 1349;BA.debugLine="If test=1 Then";
if (_test==1) { 
 //BA.debugLineNum = 1350;BA.debugLine="tiempo_minimo=tiempo_minimo*10";
_tiempo_minimo = (int) (_tiempo_minimo*10);
 };
 //BA.debugLineNum = 1354;BA.debugLine="If test=2 Then";
if (_test==2) { 
 //BA.debugLineNum = 1355;BA.debugLine="tiempo_minimo=tiempo_minimo*600";
_tiempo_minimo = (int) (_tiempo_minimo*600);
 };
 };
 //BA.debugLineNum = 1361;BA.debugLine="Log(\"Ultimo tiemoi \"&tiempo_minimo)";
anywheresoftware.b4a.keywords.Common.Log("Ultimo tiemoi "+BA.NumberToString(_tiempo_minimo));
 };
 //BA.debugLineNum = 1364;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1365;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1366;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1367;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1368;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1369;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1370;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1371;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1372;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1373;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1374;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1375;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1376;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1378;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 1837;BA.debugLine="Sub mirar_tiempo_servo(servo_actual As Int, tiempo";
 //BA.debugLineNum = 1838;BA.debugLine="Dim numerolineas As Int";
_numerolineas = 0;
 //BA.debugLineNum = 1839;BA.debugLine="Dim aux_servo As Int";
_aux_servo = 0;
 //BA.debugLineNum = 1840;BA.debugLine="Dim aux_tiempo As Int";
_aux_tiempo = 0;
 //BA.debugLineNum = 1841;BA.debugLine="Dim aux_tipo As String";
_aux_tipo = "";
 //BA.debugLineNum = 1842;BA.debugLine="numerolineas = lvDatos.Size";
_numerolineas = mostCurrent._lvdatos.getSize();
 //BA.debugLineNum = 1843;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 1844;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 1845;BA.debugLine="Dim num As Int";
_num = 0;
 //BA.debugLineNum = 1846;BA.debugLine="num =numerolineas-5";
_num = (int) (_numerolineas-5);
 //BA.debugLineNum = 1847;BA.debugLine="Dim servo_actual_nombre As String";
_servo_actual_nombre = "";
 //BA.debugLineNum = 1851;BA.debugLine="If servo_actual=1 Then";
if (_servo_actual==1) { 
 //BA.debugLineNum = 1852;BA.debugLine="servo_actual_nombre=\"C.INCI.ALAS 1\"";
_servo_actual_nombre = "C.INCI.ALAS 1";
 };
 //BA.debugLineNum = 1854;BA.debugLine="If servo_actual=2 Then";
if (_servo_actual==2) { 
 //BA.debugLineNum = 1855;BA.debugLine="servo_actual_nombre=\"CORTE.MOTOR 2\"";
_servo_actual_nombre = "CORTE.MOTOR 2";
 };
 //BA.debugLineNum = 1857;BA.debugLine="If servo_actual =3 Then";
if (_servo_actual==3) { 
 //BA.debugLineNum = 1858;BA.debugLine="servo_actual_nombre=\"INC.STABILO 3\"";
_servo_actual_nombre = "INC.STABILO 3";
 };
 //BA.debugLineNum = 1860;BA.debugLine="If servo_actual=4 Then";
if (_servo_actual==4) { 
 //BA.debugLineNum = 1861;BA.debugLine="servo_actual_nombre=\"TIMON DIREC 4\"";
_servo_actual_nombre = "TIMON DIREC 4";
 };
 //BA.debugLineNum = 1863;BA.debugLine="If servo_actual=5 Then";
if (_servo_actual==5) { 
 //BA.debugLineNum = 1864;BA.debugLine="servo_actual_nombre=\"FLAP-FOLDER 5\"";
_servo_actual_nombre = "FLAP-FOLDER 5";
 };
 //BA.debugLineNum = 1869;BA.debugLine="Log(servo_actual_nombre &\"tiempo Actual \"&tiempo_a";
anywheresoftware.b4a.keywords.Common.Log(_servo_actual_nombre+"tiempo Actual "+BA.NumberToString(_tiempo_actual1)+" Numero_lineas "+BA.NumberToString(_numerolineas)+"numero lineas-5 "+BA.NumberToString(_num));
 //BA.debugLineNum = 1870;BA.debugLine="For  i= num To  numerolineas-1";
{
final int step27 = 1;
final int limit27 = (int) (_numerolineas-1);
for (_i = _num ; (step27 > 0 && _i <= limit27) || (step27 < 0 && _i >= limit27); _i = ((int)(0 + _i + step27)) ) {
 //BA.debugLineNum = 1872;BA.debugLine="If i<10 Then";
if (_i<10) { 
 }else {
 //BA.debugLineNum = 1877;BA.debugLine="str =lvDatos.GetItem(lvDatos.Size-1)";
_str = BA.ObjectToString(mostCurrent._lvdatos.GetItem((int) (mostCurrent._lvdatos.getSize()-1)));
 //BA.debugLineNum = 1878;BA.debugLine="aux_servo = str.SubString2(12,13)";
_aux_servo = (int)(Double.parseDouble(_str.substring((int) (12),(int) (13))));
 //BA.debugLineNum = 1879;BA.debugLine="Log(lvDatos.GetItem(lvDatos.Size-1))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lvdatos.GetItem((int) (mostCurrent._lvdatos.getSize()-1))));
 //BA.debugLineNum = 1880;BA.debugLine="aux_tiempo=str.SubString2(36,40)";
_aux_tiempo = (int)(Double.parseDouble(_str.substring((int) (36),(int) (40))));
 //BA.debugLineNum = 1881;BA.debugLine="aux_tipo=str.SubString2(55,56)";
_aux_tipo = _str.substring((int) (55),(int) (56));
 //BA.debugLineNum = 1882;BA.debugLine="Log(\"cadena \"&str)";
anywheresoftware.b4a.keywords.Common.Log("cadena "+_str);
 //BA.debugLineNum = 1884;BA.debugLine="Log(\"aux_servo \"&aux_servo)";
anywheresoftware.b4a.keywords.Common.Log("aux_servo "+BA.NumberToString(_aux_servo));
 //BA.debugLineNum = 1885;BA.debugLine="Log(\"aux_tiempo \"&aux_tiempo)";
anywheresoftware.b4a.keywords.Common.Log("aux_tiempo "+BA.NumberToString(_aux_tiempo));
 //BA.debugLineNum = 1886;BA.debugLine="Log(\"aux_tipo \"&aux_tipo)";
anywheresoftware.b4a.keywords.Common.Log("aux_tipo "+_aux_tipo);
 //BA.debugLineNum = 1890;BA.debugLine="If aux_tipo=\"D\" Then";
if ((_aux_tipo).equals("D")) { 
 //BA.debugLineNum = 1891;BA.debugLine="aux_tiempo=aux_tiempo";
_aux_tiempo = _aux_tiempo;
 //BA.debugLineNum = 1892;BA.debugLine="Log(\"Tiempo Actual decimas convertir decimas: \"&au";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual decimas convertir decimas: "+BA.NumberToString(_aux_tiempo));
 };
 //BA.debugLineNum = 1895;BA.debugLine="If aux_tipo=\"S\" Then";
if ((_aux_tipo).equals("S")) { 
 //BA.debugLineNum = 1896;BA.debugLine="aux_tiempo=aux_tiempo*10";
_aux_tiempo = (int) (_aux_tiempo*10);
 //BA.debugLineNum = 1897;BA.debugLine="Log(\"Tiempo Actual segundos convertir decimas: \"&a";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos convertir decimas: "+BA.NumberToString(_aux_tiempo));
 };
 //BA.debugLineNum = 1899;BA.debugLine="If aux_tipo=\"M\" Then";
if ((_aux_tipo).equals("M")) { 
 //BA.debugLineNum = 1900;BA.debugLine="aux_tiempo=aux_tiempo*600";
_aux_tiempo = (int) (_aux_tiempo*600);
 //BA.debugLineNum = 1901;BA.debugLine="Log(\"Tiempo Actual minutos convertir decimas: \"&au";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos convertir decimas: "+BA.NumberToString(_aux_tiempo));
 };
 //BA.debugLineNum = 1910;BA.debugLine="If aux_tiempo>=tiempo_actual1 Then";
if (_aux_tiempo>=_tiempo_actual1) { 
 //BA.debugLineNum = 1912;BA.debugLine="If aux_servo=servo_actual Then";
if (_aux_servo==_servo_actual) { 
 //BA.debugLineNum = 1914;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1916;BA.debugLine="Msgbox(\"Mismo TIEMPO y mismo SERVO\",\"ERROR\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Mismo TIEMPO y mismo SERVO","ERROR",mostCurrent.activityBA);
 //BA.debugLineNum = 1918;BA.debugLine="ex=False";
_ex = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1919;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1920;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1921;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1922;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1923;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1924;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1925;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1926;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1927;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1928;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1929;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1930;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1931;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1932;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 1934;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1935;BA.debugLine="tiempo=tiempo_actual1";
mostCurrent._tiempo = BA.NumberToString(_tiempo_actual1);
 //BA.debugLineNum = 1936;BA.debugLine="Log(\"Tiempo final : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo final : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1939;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1940;BA.debugLine="tiempo=tiempo_actual1/10";
mostCurrent._tiempo = BA.NumberToString(_tiempo_actual1/(double)10);
 //BA.debugLineNum = 1941;BA.debugLine="Log(\"Tiempo final : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo final : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1943;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1944;BA.debugLine="tiempo=tiempo_actual1/600";
mostCurrent._tiempo = BA.NumberToString(_tiempo_actual1/(double)600);
 //BA.debugLineNum = 1945;BA.debugLine="Log(\"Tiempo final : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo final : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1948;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1949;BA.debugLine="ex=True";
_ex = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1950;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 };
 }
};
 //BA.debugLineNum = 1966;BA.debugLine="End Sub";
return false;
}
public static String  _mod_datos(int _valor_ini,int _valor_fin,int _valor_sum) throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
 //BA.debugLineNum = 2448;BA.debugLine="Sub mod_datos (valor_ini As Int, valor_fin As Int,";
 //BA.debugLineNum = 2449;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 2450;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 2451;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 2452;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 2453;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 2457;BA.debugLine="If posicion_mod<valor_fin Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<_valor_fin) { 
 //BA.debugLineNum = 2458;BA.debugLine="If posicion_mod>=valor_ini Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=_valor_ini) { 
 //BA.debugLineNum = 2459;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 2460;BA.debugLine="pos_valor=(pos_mod_aux*4)+valor_sum";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+_valor_sum);
 //BA.debugLineNum = 2463;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 2464;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 2465;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 2468;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 2469;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 2470;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 2472;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 2476;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 2479;BA.debugLine="lista1.Set(pos_valor,tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(mostCurrent._tiempo));
 };
 //BA.debugLineNum = 2483;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 2484;BA.debugLine="lista1.set(pos_valor+1,\"0\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("0"));
 };
 //BA.debugLineNum = 2486;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 2487;BA.debugLine="lista1.set(pos_valor+1,\"1\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("1"));
 };
 //BA.debugLineNum = 2489;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 2490;BA.debugLine="lista1.set(pos_valor+1,\"2\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("2"));
 };
 //BA.debugLineNum = 2493;BA.debugLine="lista1.set(pos_valor+2,servo)";
mostCurrent._lista1.Set((int) (_pos_valor+2),(Object)(mostCurrent._servo));
 //BA.debugLineNum = 2496;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 2497;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2498;BA.debugLine="lista1.set(pos_valor+3,aux_valor_giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 2501;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 2502;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 2503;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 2504;BA.debugLine="lista1.set(pos_valor+3,aux_valor_giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 2508;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 2509;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 };
 };
 //BA.debugLineNum = 2518;BA.debugLine="End Sub";
return "";
}
public static String  _mod_datos_button2() throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
int _i = 0;
 //BA.debugLineNum = 1617;BA.debugLine="Sub mod_datos_button2";
 //BA.debugLineNum = 1618;BA.debugLine="Try";
try { //BA.debugLineNum = 1619;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 1620;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 1621;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 1622;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 1623;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 1624;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1625;BA.debugLine="tiempo=tiempo";
mostCurrent._tiempo = mostCurrent._tiempo;
 //BA.debugLineNum = 1626;BA.debugLine="Log(\"Tiempo Actual decimas: \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual decimas: "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1639;BA.debugLine="If posicion_mod<20 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<20) { 
 //BA.debugLineNum = 1640;BA.debugLine="If posicion_mod>=10 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=10) { 
 //BA.debugLineNum = 1641;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1642;BA.debugLine="tiempo=tiempo/10";
mostCurrent._tiempo = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo))/(double)10);
 //BA.debugLineNum = 1643;BA.debugLine="Log(\"Tiempo Actual segundos : \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual segundos : "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1645;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1646;BA.debugLine="tiempo=tiempo/600";
mostCurrent._tiempo = BA.NumberToString((double)(Double.parseDouble(mostCurrent._tiempo))/(double)600);
 //BA.debugLineNum = 1647;BA.debugLine="Log(\"Tiempo Actual minutos: \"&tiempo)";
anywheresoftware.b4a.keywords.Common.Log("Tiempo Actual minutos: "+mostCurrent._tiempo);
 };
 //BA.debugLineNum = 1650;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1651;BA.debugLine="pos_valor=(pos_mod_aux*4)+10";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+10);
 //BA.debugLineNum = 1654;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 1656;BA.debugLine="aux_valor_tiempo=\"00\"&tiempo";
_aux_valor_tiempo = "00"+mostCurrent._tiempo;
 //BA.debugLineNum = 1657;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 1660;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 1661;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 1663;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 1664;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 1668;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 1669;BA.debugLine="aux_valor_tiempo=tiempo";
_aux_valor_tiempo = mostCurrent._tiempo;
 //BA.debugLineNum = 1671;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 1675;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 1676;BA.debugLine="lista1.set(pos_valor+1,\"0\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("0"));
 };
 //BA.debugLineNum = 1678;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 1679;BA.debugLine="lista1.set(pos_valor+1,\"1\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("1"));
 };
 //BA.debugLineNum = 1681;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 1682;BA.debugLine="lista1.set(pos_valor+1,\"2\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("2"));
 };
 //BA.debugLineNum = 1685;BA.debugLine="lista1.set(pos_valor+2,servo)";
mostCurrent._lista1.Set((int) (_pos_valor+2),(Object)(mostCurrent._servo));
 //BA.debugLineNum = 1688;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 1690;BA.debugLine="giro=\"00\"&giro";
mostCurrent._giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 1692;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 //BA.debugLineNum = 1695;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 1696;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 1698;BA.debugLine="giro=\"0\"&giro";
mostCurrent._giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 1700;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 };
 //BA.debugLineNum = 1704;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 1705;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 };
 };
 //BA.debugLineNum = 1711;BA.debugLine="mod_datos(20,30,50)";
_mod_datos((int) (20),(int) (30),(int) (50));
 //BA.debugLineNum = 1713;BA.debugLine="mod_datos(30,40,90)";
_mod_datos((int) (30),(int) (40),(int) (90));
 //BA.debugLineNum = 1715;BA.debugLine="mod_datos(40,50,130)";
_mod_datos((int) (40),(int) (50),(int) (130));
 //BA.debugLineNum = 1717;BA.debugLine="mod_datos(50,60,170)";
_mod_datos((int) (50),(int) (60),(int) (170));
 //BA.debugLineNum = 1726;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola/F";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 1727;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 1742;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1743;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (0)))+"  INICIO");
 //BA.debugLineNum = 1744;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1745;BA.debugLine="lvDatos.AddSingleLine(\"CORTE.MOTOR 2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("CORTE.MOTOR 2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (1)))+"  INICIO");
 //BA.debugLineNum = 1746;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1747;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (2)))+"  INICIO");
 //BA.debugLineNum = 1748;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1749;BA.debugLine="lvDatos.AddSingleLine(\"TIMON DIREC 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("TIMON DIREC 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (3)))+"  INICIO");
 //BA.debugLineNum = 1750;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1751;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (4)))+"  INICIO");
 //BA.debugLineNum = 1756;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColo";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1757;BA.debugLine="lvDatos.AddSingleLine(\"C.INCI.ALAS 1   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("C.INCI.ALAS 1   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (5)))+"  RDT");
 //BA.debugLineNum = 1758;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1759;BA.debugLine="lvDatos.AddSingleLine(\"CORTE.MOTOR 2   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("CORTE.MOTOR 2   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (6)))+"  RDT");
 //BA.debugLineNum = 1760;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1761;BA.debugLine="lvDatos.AddSingleLine(\"INC.STABILO 3   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("INC.STABILO 3   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (7)))+"  RDT");
 //BA.debugLineNum = 1762;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1763;BA.debugLine="lvDatos.AddSingleLine(\"TIMON DIREC 4   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("TIMON DIREC 4   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (8)))+"  RDT");
 //BA.debugLineNum = 1764;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Col";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1765;BA.debugLine="lvDatos.AddSingleLine(\"FLAP-FOLDER 5   Giro: \"";
mostCurrent._lvdatos.AddSingleLine("FLAP-FOLDER 5   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get((int) (9)))+"  RDT");
 //BA.debugLineNum = 1767;BA.debugLine="For i=10 To lista1.Size-1 Step 4";
{
final int step88 = (int) (4);
final int limit88 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (10) ; (step88 > 0 && _i <= limit88) || (step88 < 0 && _i >= limit88); _i = ((int)(0 + _i + step88)) ) {
 //BA.debugLineNum = 1768;BA.debugLine="tiempo_aux=lista1.Get(i)";
mostCurrent._tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get(_i));
 //BA.debugLineNum = 1769;BA.debugLine="tipo_tiempo_aux=lista1.Get(i+1)";
mostCurrent._tipo_tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+1)));
 //BA.debugLineNum = 1770;BA.debugLine="servo_aux=lista1.Get(i+2)";
mostCurrent._servo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+2)));
 //BA.debugLineNum = 1772;BA.debugLine="If servo_aux=1 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1773;BA.debugLine="servo_aux_nombre=\"C.INCI.ALAS 1\"";
mostCurrent._servo_aux_nombre = "C.INCI.ALAS 1";
 };
 //BA.debugLineNum = 1775;BA.debugLine="If servo_aux=2 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1776;BA.debugLine="servo_aux_nombre=\"CORTE.MOTOR 2\"";
mostCurrent._servo_aux_nombre = "CORTE.MOTOR 2";
 };
 //BA.debugLineNum = 1778;BA.debugLine="If servo_aux=3 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 1779;BA.debugLine="servo_aux_nombre=\"INC.STABILO 3\"";
mostCurrent._servo_aux_nombre = "INC.STABILO 3";
 };
 //BA.debugLineNum = 1781;BA.debugLine="If servo_aux=4 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 1782;BA.debugLine="servo_aux_nombre=\"TIMON DIREC 4\"";
mostCurrent._servo_aux_nombre = "TIMON DIREC 4";
 };
 //BA.debugLineNum = 1784;BA.debugLine="If servo_aux=5 Then";
if ((mostCurrent._servo_aux).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 1785;BA.debugLine="servo_aux_nombre=\"FLAP-FOLDER 5\"";
mostCurrent._servo_aux_nombre = "FLAP-FOLDER 5";
 };
 //BA.debugLineNum = 1789;BA.debugLine="giro_aux=lista1.Get(i+3)";
mostCurrent._giro_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+3)));
 //BA.debugLineNum = 1790;BA.debugLine="If tipo_tiempo_aux=0 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 1791;BA.debugLine="tipo_tiempo_aux=\"DECIMAS\"";
mostCurrent._tipo_tiempo_aux = "DECIMAS";
 };
 //BA.debugLineNum = 1794;BA.debugLine="If tipo_tiempo_aux=1 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 1795;BA.debugLine="tipo_tiempo_aux=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo_aux = "SEGUNDOS";
 };
 //BA.debugLineNum = 1798;BA.debugLine="If tipo_tiempo_aux=2 Then";
if ((mostCurrent._tipo_tiempo_aux).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 1799;BA.debugLine="tipo_tiempo_aux=\"MINUTOS\"";
mostCurrent._tipo_tiempo_aux = "MINUTOS";
 };
 //BA.debugLineNum = 1801;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1802;BA.debugLine="lvDatos.AddSingleLine(servo_aux_nombre&\"   Gir";
mostCurrent._lvdatos.AddSingleLine(mostCurrent._servo_aux_nombre+"   Giro: "+mostCurrent._giro_aux+"    Tiempo: "+mostCurrent._tiempo_aux+"  Tipo tiempo: "+mostCurrent._tipo_tiempo_aux);
 }
};
 //BA.debugLineNum = 1805;BA.debugLine="tiempo_minimo=aux_valor_tiempo";
_tiempo_minimo = (int)(Double.parseDouble(_aux_valor_tiempo));
 //BA.debugLineNum = 1806;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1807;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1808;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1809;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1812;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 1813;BA.debugLine="lbservotxt.Text=\"\"";
mostCurrent._lbservotxt.setText((Object)(""));
 //BA.debugLineNum = 1814;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1815;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 1816;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 1817;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 1818;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 //BA.debugLineNum = 1819;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 1820;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1821;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1822;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1823;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1826;BA.debugLine="etGiro.Enabled=False";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1827;BA.debugLine="etTiempo.Enabled=False";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1828;BA.debugLine="spTiempo.Enabled=False";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e1303) {
			processBA.setLastException(e1303); };
 //BA.debugLineNum = 1835;BA.debugLine="End Sub";
return "";
}
public static String  _mod_tiempo(int _pos) throws Exception{
int _pos_valor = 0;
String _pos_mod_aux = "";
int _size = 0;
 //BA.debugLineNum = 1114;BA.debugLine="Sub mod_tiempo(pos As Int)";
 //BA.debugLineNum = 1115;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 1116;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 1117;BA.debugLine="Dim size As Int";
_size = 0;
 //BA.debugLineNum = 1118;BA.debugLine="size = lvDatos.size-1";
_size = (int) (mostCurrent._lvdatos.getSize()-1);
 //BA.debugLineNum = 1119;BA.debugLine="If pos=10 Then";
if (_pos==10) { 
 //BA.debugLineNum = 1120;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 1121;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1122;BA.debugLine="pos_valor=(pos_mod_aux*4)+10";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+10);
 }else {
 //BA.debugLineNum = 1130;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1131;BA.debugLine="pos_valor=(pos_mod_aux*4)+10";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+10);
 //BA.debugLineNum = 1132;BA.debugLine="tiempo_ante_lv=0";
mostCurrent._tiempo_ante_lv = BA.NumberToString(0);
 //BA.debugLineNum = 1133;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 1134;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 1135;BA.debugLine="servo_ante_lv=0";
mostCurrent._servo_ante_lv = BA.NumberToString(0);
 //BA.debugLineNum = 1136;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 //BA.debugLineNum = 1141;BA.debugLine="If pos<20 Then";
if (_pos<20) { 
 //BA.debugLineNum = 1142;BA.debugLine="If pos>10 Then";
if (_pos>10) { 
 //BA.debugLineNum = 1143;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 1144;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1145;BA.debugLine="pos_valor=(pos_mod_aux*4)+10";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+10);
 //BA.debugLineNum = 1146;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1147;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 1148;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1149;BA.debugLine="tiempo_post_lv=9999999999999999999";
mostCurrent._tiempo_post_lv = BA.NumberToString(9999999999999999999d);
 //BA.debugLineNum = 1150;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 //BA.debugLineNum = 1152;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1153;BA.debugLine="pos_valor=(pos_mod_aux*4)+10";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+10);
 //BA.debugLineNum = 1154;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1155;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 1156;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 1157;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1158;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 };
 //BA.debugLineNum = 1167;BA.debugLine="If pos<30 Then";
if (_pos<30) { 
 //BA.debugLineNum = 1168;BA.debugLine="If pos>=20 Then";
if (_pos>=20) { 
 //BA.debugLineNum = 1169;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 1170;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1171;BA.debugLine="pos_valor=(pos_mod_aux*4)+50";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+50);
 //BA.debugLineNum = 1172;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1173;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 1174;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1175;BA.debugLine="tiempo_post_lv=\"NO\"";
mostCurrent._tiempo_post_lv = "NO";
 //BA.debugLineNum = 1176;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 //BA.debugLineNum = 1178;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1179;BA.debugLine="pos_valor=(pos_mod_aux*4)+50";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+50);
 //BA.debugLineNum = 1180;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1181;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 1182;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 1183;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1184;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 };
 //BA.debugLineNum = 1195;BA.debugLine="If pos<40 Then";
if (_pos<40) { 
 //BA.debugLineNum = 1196;BA.debugLine="If pos>=30 Then";
if (_pos>=30) { 
 //BA.debugLineNum = 1197;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 1198;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1199;BA.debugLine="pos_valor=(pos_mod_aux*4)+90";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+90);
 //BA.debugLineNum = 1200;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1201;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 1202;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1203;BA.debugLine="tiempo_post_lv=\"NO\"";
mostCurrent._tiempo_post_lv = "NO";
 //BA.debugLineNum = 1204;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 //BA.debugLineNum = 1206;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1207;BA.debugLine="pos_valor=(pos_mod_aux*4)+90";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+90);
 //BA.debugLineNum = 1208;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1209;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 1210;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 1211;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1212;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 };
 //BA.debugLineNum = 1225;BA.debugLine="If pos<50 Then";
if (_pos<50) { 
 //BA.debugLineNum = 1226;BA.debugLine="If pos>=40 Then";
if (_pos>=40) { 
 //BA.debugLineNum = 1227;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 1228;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1229;BA.debugLine="pos_valor=(pos_mod_aux*4)+130";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+130);
 //BA.debugLineNum = 1230;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1231;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 1232;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1233;BA.debugLine="tiempo_post_lv=\"NO\"";
mostCurrent._tiempo_post_lv = "NO";
 //BA.debugLineNum = 1234;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 //BA.debugLineNum = 1236;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1237;BA.debugLine="pos_valor=(pos_mod_aux*4)+130";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+130);
 //BA.debugLineNum = 1238;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1239;BA.debugLine="tiempo_post_lv=lista1.Get(pos_valor+4)";
mostCurrent._tiempo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+4)));
 //BA.debugLineNum = 1240;BA.debugLine="tIPO_POST_LV=lista1.Get(pos_valor+5)";
mostCurrent._tipo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+5)));
 //BA.debugLineNum = 1241;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1242;BA.debugLine="servo_post_lv=lista1.Get(pos_valor+6)";
mostCurrent._servo_post_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor+6)));
 };
 };
 };
 //BA.debugLineNum = 1251;BA.debugLine="If pos<60 Then";
if (_pos<60) { 
 //BA.debugLineNum = 1252;BA.debugLine="If pos>=50 Then";
if (_pos>=50) { 
 //BA.debugLineNum = 1253;BA.debugLine="If pos=size Then";
if (_pos==_size) { 
 //BA.debugLineNum = 1254;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 1255;BA.debugLine="pos_valor=(pos_mod_aux*4)+170";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+170);
 //BA.debugLineNum = 1256;BA.debugLine="tiempo_ante_lv=lista1.Get(pos_valor-4)";
mostCurrent._tiempo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-4)));
 //BA.debugLineNum = 1257;BA.debugLine="TIPO_ANTE_LV=lista1.Get(pos_valor-3)";
mostCurrent._tipo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-3)));
 //BA.debugLineNum = 1258;BA.debugLine="servo_ante_lv=lista1.Get(pos_valor-2)";
mostCurrent._servo_ante_lv = BA.ObjectToString(mostCurrent._lista1.Get((int) (_pos_valor-2)));
 //BA.debugLineNum = 1259;BA.debugLine="tiempo_post_lv=\"NO\"";
mostCurrent._tiempo_post_lv = "NO";
 //BA.debugLineNum = 1260;BA.debugLine="servo_post_lv=\"NO\"";
mostCurrent._servo_post_lv = "NO";
 }else {
 };
 };
 };
 //BA.debugLineNum = 1274;BA.debugLine="End Sub";
return "";
}
public static String  _openusb() throws Exception{
int _dev = 0;
String _flag_inicio_usb = "";
 //BA.debugLineNum = 2686;BA.debugLine="Sub openusb";
 //BA.debugLineNum = 2687;BA.debugLine="If usb1.UsbPresent(1)=usb1.USB_NONE Then";
if (_usb1.UsbPresent((int) (1))==_usb1.USB_NONE) { 
 //BA.debugLineNum = 2689;BA.debugLine="Log(\"Msgbox - no device\")";
anywheresoftware.b4a.keywords.Common.Log("Msgbox - no device");
 //BA.debugLineNum = 2690;BA.debugLine="Msgbox(\"No USB device or accessory detected!\", \"";
anywheresoftware.b4a.keywords.Common.Msgbox("No USB device or accessory detected!","Error",mostCurrent.activityBA);
 //BA.debugLineNum = 2691;BA.debugLine="Log(\"Msgbox - returned\")";
anywheresoftware.b4a.keywords.Common.Log("Msgbox - returned");
 };
 //BA.debugLineNum = 2693;BA.debugLine="Log(\"Checking permission 1\")";
anywheresoftware.b4a.keywords.Common.Log("Checking permission 1");
 //BA.debugLineNum = 2694;BA.debugLine="If (usb1.HasPermission(1)) Then	' Ver_2.4";
if ((_usb1.HasPermission((int) (1)))) { 
 //BA.debugLineNum = 2696;BA.debugLine="Dim dev As Int";
_dev = 0;
 //BA.debugLineNum = 2698;BA.debugLine="dev = usb1.Open(9600,1)		' Ver_2.4";
_dev = _usb1.Open(processBA,(int) (9600),(int) (1));
 //BA.debugLineNum = 2699;BA.debugLine="If dev <> usb1.USB_NONE Then";
if (_dev!=_usb1.USB_NONE) { 
 //BA.debugLineNum = 2700;BA.debugLine="Log(\"Connected successfully! 1\")";
anywheresoftware.b4a.keywords.Common.Log("Connected successfully! 1");
 //BA.debugLineNum = 2701;BA.debugLine="astreams1.Initialize(usb1.GetInputStream, usb";
_astreams1.Initialize(processBA,_usb1.GetInputStream(),_usb1.GetOutputStream(),"astreams1");
 //BA.debugLineNum = 2702;BA.debugLine="flag_inicio_usb=True";
_flag_inicio_usb = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 2704;BA.debugLine="Log(\"Error opening USB port 1\")";
anywheresoftware.b4a.keywords.Common.Log("Error opening USB port 1");
 };
 }else {
 //BA.debugLineNum = 2707;BA.debugLine="usb1.requestPermission(1)  ' Ver_2.4";
_usb1.RequestPermission((int) (1));
 };
 //BA.debugLineNum = 2709;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim astreams1 As AsyncStreams";
_astreams1 = new anywheresoftware.b4a.randomaccessfile.AsyncStreams();
 //BA.debugLineNum = 13;BA.debugLine="Dim flag As Int=0";
_flag = (int) (0);
 //BA.debugLineNum = 14;BA.debugLine="Dim strModelo As String";
_strmodelo = "";
 //BA.debugLineNum = 15;BA.debugLine="Dim usb1 As UsbSerial";
_usb1 = new anywheresoftware.b4a.objects.UsbSerial();
 //BA.debugLineNum = 16;BA.debugLine="Dim strspinner As String";
_strspinner = "";
 //BA.debugLineNum = 17;BA.debugLine="Dim strfichero As String";
_strfichero = "";
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _send_data(String _data) throws Exception{
byte[] _buffer = null;
 //BA.debugLineNum = 2808;BA.debugLine="Sub send_data(data As String)";
 //BA.debugLineNum = 2810;BA.debugLine="Dim buffer() As Byte";
_buffer = new byte[(int) (0)];
;
 //BA.debugLineNum = 2812;BA.debugLine="buffer = data.GetBytes(\"UTF8\")";
_buffer = _data.getBytes("UTF8");
 //BA.debugLineNum = 2813;BA.debugLine="Log(\"data \"&data)";
anywheresoftware.b4a.keywords.Common.Log("data "+_data);
 //BA.debugLineNum = 2814;BA.debugLine="Log(\"Buffer \"&buffer(0))";
anywheresoftware.b4a.keywords.Common.Log("Buffer "+BA.NumberToString(_buffer[(int) (0)]));
 //BA.debugLineNum = 2815;BA.debugLine="t=t+1";
_t = (int) (_t+1);
 //BA.debugLineNum = 2816;BA.debugLine="Log(\"contador: \"&t)";
anywheresoftware.b4a.keywords.Common.Log("contador: "+BA.NumberToString(_t));
 //BA.debugLineNum = 2817;BA.debugLine="astreams1.Write(buffer)";
_astreams1.Write(_buffer);
 //BA.debugLineNum = 2818;BA.debugLine="End Sub";
return "";
}
public static String  _spconfig_itemclick(int _position,Object _value) throws Exception{
int _numpos = 0;
 //BA.debugLineNum = 561;BA.debugLine="Sub spconfig_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 562;BA.debugLine="Dim numpos As Int";
_numpos = 0;
 //BA.debugLineNum = 563;BA.debugLine="Try";
try { //BA.debugLineNum = 565;BA.debugLine="If Value=\"PULSE AQUI\" Then";
if ((_value).equals((Object)("PULSE AQUI"))) { 
 //BA.debugLineNum = 567;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 570;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 571;BA.debugLine="config=\" \"&Value";
mostCurrent._config = " "+BA.ObjectToString(_value);
 //BA.debugLineNum = 573;BA.debugLine="NOMBRE_FICHERO=strfichero&config";
mostCurrent._nombre_fichero = _strfichero+mostCurrent._config;
 //BA.debugLineNum = 574;BA.debugLine="etFichero.Text=NOMBRE_FICHERO";
mostCurrent._etfichero.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 575;BA.debugLine="spconfig.Enabled=False";
mostCurrent._spconfig.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 577;BA.debugLine="lista1.Clear";
mostCurrent._lista1.Clear();
 //BA.debugLineNum = 580;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 581;BA.debugLine="strModelo=\"Modelo_F1C_\"&NOMBRE_FICHERO";
_strmodelo = "Modelo_F1C_"+mostCurrent._nombre_fichero;
 //BA.debugLineNum = 582;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 583;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 584;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 585;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 586;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 587;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 588;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 589;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 590;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 591;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 592;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 593;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 594;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 595;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 596;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 597;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 //BA.debugLineNum = 598;BA.debugLine="lbNumeroServo.Text=\"C.I.ALA\"";
mostCurrent._lbnumeroservo.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 599;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 600;BA.debugLine="btOK.Enabled=True";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 601;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 602;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 603;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 604;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 605;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 606;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 607;BA.debugLine="btOK.Visible=True";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 609;BA.debugLine="tiempo_actual=0";
_tiempo_actual = (int) (0);
 //BA.debugLineNum = 610;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 611;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 };
 //BA.debugLineNum = 614;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 } 
       catch (Exception e465) {
			processBA.setLastException(e465); //BA.debugLineNum = 616;BA.debugLine="NOMBRE_FICHERO=etFichero.Text";
mostCurrent._nombre_fichero = mostCurrent._etfichero.getText();
 //BA.debugLineNum = 618;BA.debugLine="If File.Exists(File.DirRootExternal & \"/Consola/";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 619;BA.debugLine="lista1.Clear";
mostCurrent._lista1.Clear();
 //BA.debugLineNum = 622;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 623;BA.debugLine="strModelo=\"Modelo_F1C_\"&NOMBRE_FICHERO";
_strmodelo = "Modelo_F1C_"+mostCurrent._nombre_fichero;
 //BA.debugLineNum = 624;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 625;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 626;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 627;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 628;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 629;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 630;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 631;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 632;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 633;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 634;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 635;BA.debugLine="Panel2.Visible=True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 636;BA.debugLine="Label5.Visible=True";
mostCurrent._label5.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 637;BA.debugLine="lbNumeroServo.Visible=False'true";
mostCurrent._lbnumeroservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 638;BA.debugLine="lbservotxt.Visible=True";
mostCurrent._lbservotxt.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 639;BA.debugLine="tiempo_minimo=0";
_tiempo_minimo = (int) (0);
 //BA.debugLineNum = 640;BA.debugLine="lbNumeroServo.Text=\"C.I.ALA\"";
mostCurrent._lbnumeroservo.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 641;BA.debugLine="lbservotxt.Text=\"C.I.ALA\"";
mostCurrent._lbservotxt.setText((Object)("C.I.ALA"));
 //BA.debugLineNum = 642;BA.debugLine="btOK.Enabled=True";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 643;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 644;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 645;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 646;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 647;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 648;BA.debugLine="etGiro.Enabled=True";
mostCurrent._etgiro.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 649;BA.debugLine="btOK.Visible=True";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 651;BA.debugLine="tiempo_actual=0";
_tiempo_actual = (int) (0);
 //BA.debugLineNum = 652;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consola";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 653;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 };
 //BA.debugLineNum = 656;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 };
 //BA.debugLineNum = 663;BA.debugLine="listaconfiguracion.Set(1,NOMBRE_FICHERO)";
mostCurrent._listaconfiguracion.Set((int) (1),(Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 665;BA.debugLine="File.WriteList(File.DirRootExternal & \"/Consol";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/Consola/F1C/configuracion","Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._listaconfiguracion);
 //BA.debugLineNum = 668;BA.debugLine="End Sub";
return "";
}
public static String  _spservo_itemclick(int _position,Object _value) throws Exception{
String _nom_ser = "";
 //BA.debugLineNum = 532;BA.debugLine="Sub spServo_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 533;BA.debugLine="Dim nom_ser As String";
_nom_ser = "";
 //BA.debugLineNum = 534;BA.debugLine="If Position=0 Then";
if (_position==0) { 
 }else {
 //BA.debugLineNum = 538;BA.debugLine="servo=Position";
mostCurrent._servo = BA.NumberToString(_position);
 //BA.debugLineNum = 539;BA.debugLine="If servo=1 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(1))) { 
 //BA.debugLineNum = 540;BA.debugLine="nom_ser=\"C.I.ALA\"";
_nom_ser = "C.I.ALA";
 };
 //BA.debugLineNum = 542;BA.debugLine="If servo=2 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(2))) { 
 //BA.debugLineNum = 543;BA.debugLine="nom_ser=\"MOTOR\"";
_nom_ser = "MOTOR";
 };
 //BA.debugLineNum = 545;BA.debugLine="If servo=3 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(3))) { 
 //BA.debugLineNum = 546;BA.debugLine="nom_ser=\"STABILO\"";
_nom_ser = "STABILO";
 };
 //BA.debugLineNum = 548;BA.debugLine="If servo=4 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(4))) { 
 //BA.debugLineNum = 549;BA.debugLine="nom_ser=\"TIMON DIREC\"";
_nom_ser = "TIMON DIREC";
 };
 //BA.debugLineNum = 551;BA.debugLine="If servo=5 Then";
if ((mostCurrent._servo).equals(BA.NumberToString(5))) { 
 //BA.debugLineNum = 552;BA.debugLine="nom_ser=\"FLAP-FOLDER\"";
_nom_ser = "FLAP-FOLDER";
 };
 //BA.debugLineNum = 554;BA.debugLine="lbNumeroServo.Text=nom_ser";
mostCurrent._lbnumeroservo.setText((Object)(_nom_ser));
 //BA.debugLineNum = 555;BA.debugLine="lbservotxt.Text=nom_ser";
mostCurrent._lbservotxt.setText((Object)(_nom_ser));
 //BA.debugLineNum = 556;BA.debugLine="spTiempo.Enabled=True";
mostCurrent._sptiempo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 559;BA.debugLine="End Sub";
return "";
}
public static String  _sptiempo_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 2342;BA.debugLine="Sub spTiempo_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 2344;BA.debugLine="If Value=\"PULSE AQUI\" Then";
if ((_value).equals((Object)("PULSE AQUI"))) { 
 //BA.debugLineNum = 2345;BA.debugLine="ToastMessageShow(\"VALOR NO VALIDO\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("VALOR NO VALIDO",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 2349;BA.debugLine="tipo_tiempo=Value";
mostCurrent._tipo_tiempo = BA.ObjectToString(_value);
 //BA.debugLineNum = 2350;BA.debugLine="etTiempo.Enabled=True";
mostCurrent._ettiempo.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 2357;BA.debugLine="End Sub";
return "";
}
public static String  _tim_tick() throws Exception{
 //BA.debugLineNum = 2417;BA.debugLine="Sub tim_Tick";
 //BA.debugLineNum = 2418;BA.debugLine="tim.Enabled=False";
mostCurrent._tim.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2419;BA.debugLine="lbModelo_LongClick";
_lbmodelo_longclick();
 //BA.debugLineNum = 2421;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 2672;BA.debugLine="Sub Timer1_tick";
 //BA.debugLineNum = 2673;BA.debugLine="If usb1.UsbPresent(1)=usb1.USB_NONE Then";
if (_usb1.UsbPresent((int) (1))==_usb1.USB_NONE) { 
 //BA.debugLineNum = 2675;BA.debugLine="btgrabar.Enabled=False";
mostCurrent._btgrabar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 2676;BA.debugLine="btLeer.Enabled=False";
mostCurrent._btleer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 2678;BA.debugLine="btgrabar.Enabled=True";
mostCurrent._btgrabar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 2679;BA.debugLine="btLeer.Enabled=True";
mostCurrent._btleer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 2684;BA.debugLine="End Sub";
return "";
}
}
