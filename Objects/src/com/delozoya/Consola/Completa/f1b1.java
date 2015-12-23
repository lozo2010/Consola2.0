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

public class f1b1 extends Activity implements B4AActivity{
	public static f1b1 mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.delozoya.Consola.Completa", "com.delozoya.Consola.Completa.f1b1");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (f1b1).");
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
		activityBA = new BA(this, layout, processBA, "com.delozoya.Consola.Completa", "com.delozoya.Consola.Completa.f1b1");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.delozoya.Consola.Completa.f1b1", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (f1b1) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (f1b1) Resume **");
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
		return f1b1.class;
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
        BA.LogInfo("** Activity (f1b1) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (f1b1) Resume **");
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
public anywheresoftware.b4a.objects.ButtonWrapper _btok = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etfichero = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etgiro = null;
public anywheresoftware.b4a.objects.EditTextWrapper _ettiempo = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvdatos = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spconfig = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spservo = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog _dial = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _sptiempo = null;
public anywheresoftware.b4a.objects.LabelWrapper _label8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label9 = null;
public static String _nombre_fichero = "";
public static String _config = "";
public anywheresoftware.b4a.agraham.dialogs.InputDialog.CustomDialog2 _di = null;
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
public static String _giro_aux = "";
public static String _servo_aux = "";
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbnumeroservo = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbmodelo = null;
public anywheresoftware.b4a.objects.LabelWrapper _label10 = null;
public static String _posicion_mod = "";
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
public com.delozoya.Consola.Completa.main _main = null;
public com.delozoya.Consola.Completa.f1a _f1a = null;
public com.delozoya.Consola.Completa.f1c _f1c = null;
public com.delozoya.Consola.Completa.f1b _f1b = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 98;BA.debugLine="Activity.LoadLayout(\"f1c1\")";
mostCurrent._activity.LoadLayout("f1c1",mostCurrent.activityBA);
 //BA.debugLineNum = 99;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 100;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 101;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 106;BA.debugLine="BC1.Initialize(Colors.White,5)";
mostCurrent._bc1.Initialize(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (5));
 //BA.debugLineNum = 108;BA.debugLine="InitPanel(Panel1,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel1,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 109;BA.debugLine="InitPanel(Panel2,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel2,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 110;BA.debugLine="InitPanel(Panel3,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel3,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 111;BA.debugLine="InitPanel(Panel4,3,Colors.White,Colors.Black)";
_initpanel(mostCurrent._panel4,(float) (3),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 112;BA.debugLine="Label10.Text=\"F1-C\"";
mostCurrent._label10.setText((Object)("F1-C"));
 //BA.debugLineNum = 114;BA.debugLine="BC.Initialize(Colors.Blue,5)";
mostCurrent._bc.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Blue,(int) (5));
 //BA.debugLineNum = 117;BA.debugLine="spServo.Add(\"\")";
mostCurrent._spservo.Add("");
 //BA.debugLineNum = 118;BA.debugLine="spServo.Add(\"SERVO 1\")";
mostCurrent._spservo.Add("SERVO 1");
 //BA.debugLineNum = 119;BA.debugLine="spServo.Add(\"SERVO 2\")";
mostCurrent._spservo.Add("SERVO 2");
 //BA.debugLineNum = 120;BA.debugLine="spServo.Add(\"SERVO 3\")";
mostCurrent._spservo.Add("SERVO 3");
 //BA.debugLineNum = 121;BA.debugLine="spServo.Add(\"SERVO 4\")";
mostCurrent._spservo.Add("SERVO 4");
 //BA.debugLineNum = 122;BA.debugLine="spServo.Add(\"SERVO 5\")";
mostCurrent._spservo.Add("SERVO 5");
 //BA.debugLineNum = 125;BA.debugLine="spTiempo.Add(\"PULSE AQUI\")";
mostCurrent._sptiempo.Add("PULSE AQUI");
 //BA.debugLineNum = 126;BA.debugLine="spTiempo.Add(\"DECIMAS\")";
mostCurrent._sptiempo.Add("DECIMAS");
 //BA.debugLineNum = 127;BA.debugLine="spTiempo.Add(\"SEGUNDOS\")";
mostCurrent._sptiempo.Add("SEGUNDOS");
 //BA.debugLineNum = 128;BA.debugLine="spTiempo.Add(\"MINUTOS\")";
mostCurrent._sptiempo.Add("MINUTOS");
 //BA.debugLineNum = 130;BA.debugLine="spconfig.Add(\"PULSE AQUI\")";
mostCurrent._spconfig.Add("PULSE AQUI");
 //BA.debugLineNum = 131;BA.debugLine="spconfig.Add(\"PRUEBA\")";
mostCurrent._spconfig.Add("PRUEBA");
 //BA.debugLineNum = 132;BA.debugLine="spconfig.Add(\"PRIMERA HORA\")";
mostCurrent._spconfig.Add("PRIMERA HORA");
 //BA.debugLineNum = 133;BA.debugLine="spconfig.Add(\"VIENTO ALTO\")";
mostCurrent._spconfig.Add("VIENTO ALTO");
 //BA.debugLineNum = 134;BA.debugLine="spconfig.Add(\"VIENTO MEDIO\")";
mostCurrent._spconfig.Add("VIENTO MEDIO");
 //BA.debugLineNum = 135;BA.debugLine="spconfig.Add(\"CALMA\")";
mostCurrent._spconfig.Add("CALMA");
 //BA.debugLineNum = 136;BA.debugLine="spconfig.Add(\"TERMICA\")";
mostCurrent._spconfig.Add("TERMICA");
 //BA.debugLineNum = 138;BA.debugLine="spServo.DropdownBackgroundColor=Colors.White";
mostCurrent._spservo.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 139;BA.debugLine="spTiempo.DropdownBackgroundColor=Colors.White";
mostCurrent._sptiempo.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 140;BA.debugLine="spconfig.DropdownBackgroundColor=Colors.White";
mostCurrent._spconfig.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 142;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 143;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 144;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 145;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 147;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 148;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 149;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 150;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 151;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 152;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 153;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 //BA.debugLineNum = 154;BA.debugLine="lista1.Initialize";
mostCurrent._lista1.Initialize();
 //BA.debugLineNum = 155;BA.debugLine="elegir_modelo";
_elegir_modelo();
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 165;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 167;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 161;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 162;BA.debugLine="flag=1";
_flag = (int) (1);
 //BA.debugLineNum = 163;BA.debugLine="End Sub";
return "";
}
public static String  _borrar() throws Exception{
anywheresoftware.b4a.objects.collections.List _filestodelete = null;
int _i = 0;
 //BA.debugLineNum = 1031;BA.debugLine="Sub borrar";
 //BA.debugLineNum = 1032;BA.debugLine="Dim FilesToDelete As List";
_filestodelete = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1034;BA.debugLine="FilesToDelete.Initialize";
_filestodelete.Initialize();
 //BA.debugLineNum = 1035;BA.debugLine="FilesToDelete.AddAll(File.ListFiles(File.DirRootEx";
_filestodelete.AddAll(anywheresoftware.b4a.keywords.Common.File.ListFiles(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()));
 //BA.debugLineNum = 1037;BA.debugLine="For I = 0 To FilesToDelete.Size -1";
{
final int step4 = 1;
final int limit4 = (int) (_filestodelete.getSize()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 1039;BA.debugLine="File.Delete(File.DirRootExternal, FilesToDelet";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),BA.ObjectToString(_filestodelete.Get(_i)));
 }
};
 //BA.debugLineNum = 1043;BA.debugLine="End Sub";
return "";
}
public static String  _btcomenzar_longclick() throws Exception{
 //BA.debugLineNum = 1054;BA.debugLine="Sub btComenzar_LongClick";
 //BA.debugLineNum = 1058;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 1059;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 1060;BA.debugLine="End Sub";
return "";
}
public static String  _btok_click() throws Exception{
String _aux_valor_giro = "";
int _i = 0;
 //BA.debugLineNum = 775;BA.debugLine="Sub btOK_Click";
 //BA.debugLineNum = 776;BA.debugLine="Try";
try { //BA.debugLineNum = 777;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 779;BA.debugLine="If contador_lineas<=5 Then";
if (_contador_lineas<=5) { 
 //BA.debugLineNum = 781;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 782;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 783;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 784;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 788;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 789;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 790;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 791;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 796;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 798;BA.debugLine="lista1.Add(giro)";
mostCurrent._lista1.Add((Object)(mostCurrent._giro));
 };
 //BA.debugLineNum = 801;BA.debugLine="If contador_lineas=5 Then";
if (_contador_lineas==5) { 
 //BA.debugLineNum = 802;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 803;BA.debugLine="Label9.Visible=True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 804;BA.debugLine="lbNumeroServo.Text=\"1\"";
mostCurrent._lbnumeroservo.setText((Object)("1"));
 //BA.debugLineNum = 805;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 }else {
 //BA.debugLineNum = 807;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 808;BA.debugLine="lbNumeroServo.Text=contador_lineas";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas));
 };
 };
 //BA.debugLineNum = 816;BA.debugLine="If contador_lineas>=6 Then";
if (_contador_lineas>=6) { 
 //BA.debugLineNum = 817;BA.debugLine="If contador_lineas<=11 Then";
if (_contador_lineas<=11) { 
 //BA.debugLineNum = 818;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 820;BA.debugLine="If contador_lineas>=7 Then";
if (_contador_lineas>=7) { 
 //BA.debugLineNum = 821;BA.debugLine="servo=lbNumeroServo.Text";
mostCurrent._servo = mostCurrent._lbnumeroservo.getText();
 //BA.debugLineNum = 822;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 823;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 824;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 828;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 829;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 830;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 831;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 836;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 838;BA.debugLine="lista1.Add(giro)";
mostCurrent._lista1.Add((Object)(mostCurrent._giro));
 };
 };
 //BA.debugLineNum = 843;BA.debugLine="If contador_lineas=11 Then";
if (_contador_lineas==11) { 
 //BA.debugLineNum = 844;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 845;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 846;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 847;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 848;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 849;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 850;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 851;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 852;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 853;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step57 = 1;
final int limit57 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0) ; (step57 > 0 && _i <= limit57) || (step57 < 0 && _i >= limit57); _i = ((int)(0 + _i + step57)) ) {
 //BA.debugLineNum = 855;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 859;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 860;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 861;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 862;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 863;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 864;BA.debugLine="datos_lv";
_datos_lv();
 }else {
 //BA.debugLineNum = 867;BA.debugLine="contador_lineas=contador_lineas+1";
_contador_lineas = (int) (_contador_lineas+1);
 //BA.debugLineNum = 868;BA.debugLine="lbNumeroServo.Text=contador_lineas-6";
mostCurrent._lbnumeroservo.setText((Object)(_contador_lineas-6));
 };
 };
 };
 //BA.debugLineNum = 877;BA.debugLine="Log(\"numero lineas  \"&contador_lineas)";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString(_contador_lineas));
 //BA.debugLineNum = 879;BA.debugLine="File.WriteList(File.DirRootExternal,\"Modelo_F1C_\"";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 881;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 } 
       catch (Exception e597) {
			processBA.setLastException(e597); };
 //BA.debugLineNum = 885;BA.debugLine="End Sub";
return "";
}
public static String  _btsalir_longclick() throws Exception{
 //BA.debugLineNum = 1044;BA.debugLine="Sub btSalir_LongClick";
 //BA.debugLineNum = 1050;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 1051;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _i = 0;
 //BA.debugLineNum = 667;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 670;BA.debugLine="Try";
try { //BA.debugLineNum = 671;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 672;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 673;BA.debugLine="If contador_lineas<254 Then";
if (_contador_lineas<254) { 
 //BA.debugLineNum = 674;BA.debugLine="If contador_lineas>=12 Then";
if (_contador_lineas>=12) { 
 //BA.debugLineNum = 677;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 678;BA.debugLine="aux_valor_tiempo=\"00\"&tiempo";
_aux_valor_tiempo = "00"+mostCurrent._tiempo;
 //BA.debugLineNum = 679;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 683;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 684;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 685;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 686;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 691;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 693;BA.debugLine="aux_valor_tiempo=tiempo";
_aux_valor_tiempo = mostCurrent._tiempo;
 //BA.debugLineNum = 694;BA.debugLine="lista1.Add(aux_valor_tiempo)";
mostCurrent._lista1.Add((Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 700;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 702;BA.debugLine="lista1.Add(\"0\")";
mostCurrent._lista1.Add((Object)("0"));
 };
 //BA.debugLineNum = 704;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 706;BA.debugLine="lista1.Add(\"1\")";
mostCurrent._lista1.Add((Object)("1"));
 };
 //BA.debugLineNum = 708;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 710;BA.debugLine="lista1.Add(\"0\")";
mostCurrent._lista1.Add((Object)("0"));
 };
 //BA.debugLineNum = 713;BA.debugLine="lista1.Add(servo)";
mostCurrent._lista1.Add((Object)(mostCurrent._servo));
 //BA.debugLineNum = 714;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 715;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 716;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 720;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 721;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 722;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 723;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 728;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 729;BA.debugLine="aux_valor_giro=giro";
_aux_valor_giro = mostCurrent._giro;
 //BA.debugLineNum = 731;BA.debugLine="lista1.Add(aux_valor_giro)";
mostCurrent._lista1.Add((Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 735;BA.debugLine="log_lista";
_log_lista();
 //BA.debugLineNum = 736;BA.debugLine="contador_lineas=contador_lineas+4";
_contador_lineas = (int) (_contador_lineas+4);
 //BA.debugLineNum = 737;BA.debugLine="Log(\"numero lineas  \"&(contador_lineas-2))";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString((_contador_lineas-2)));
 };
 }else {
 //BA.debugLineNum = 745;BA.debugLine="ToastMessageShow(\"maximo lineas\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("maximo lineas",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 746;BA.debugLine="Log(\"numero lineas  \"&(contador_lineas-2))";
anywheresoftware.b4a.keywords.Common.Log("numero lineas  "+BA.NumberToString((_contador_lineas-2)));
 //BA.debugLineNum = 747;BA.debugLine="Log(\"------------------------------\")";
anywheresoftware.b4a.keywords.Common.Log("------------------------------");
 //BA.debugLineNum = 748;BA.debugLine="Log(\"------------------------------\")";
anywheresoftware.b4a.keywords.Common.Log("------------------------------");
 //BA.debugLineNum = 750;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step53 = 1;
final int limit53 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0) ; (step53 > 0 && _i <= limit53) || (step53 < 0 && _i >= limit53); _i = ((int)(0 + _i + step53)) ) {
 //BA.debugLineNum = 752;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 };
 //BA.debugLineNum = 756;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 758;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&servo&\"   Giro: \"&";
mostCurrent._lvdatos.AddSingleLine("Servo "+mostCurrent._servo+"   Giro: "+_aux_valor_giro+"    Tiempo: "+_aux_valor_tiempo+"  Tipo tiempo: "+mostCurrent._tipo_tiempo);
 //BA.debugLineNum = 759;BA.debugLine="File.WriteList(File.DirRootExternal,\"Modelo_F1C_\"";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 760;BA.debugLine="ToastMessageShow(\"Servo \"&servo&\"   Giro: \"&aux_va";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Servo "+mostCurrent._servo+"   Giro: "+_aux_valor_giro+"    Tiempo: "+_aux_valor_tiempo+"  Tipo tiempo: "+mostCurrent._tipo_tiempo,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 761;BA.debugLine="ToastMessageShow(\"Servo \"&servo&\"   Giro: \"&aux_va";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Servo "+mostCurrent._servo+"   Giro: "+_aux_valor_giro+"    Tiempo: "+_aux_valor_tiempo+"  Tipo tiempo: "+mostCurrent._tipo_tiempo,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 763;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 764;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 765;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 766;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 767;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 768;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 } 
       catch (Exception e519) {
			processBA.setLastException(e519); };
 //BA.debugLineNum = 772;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
int _i = 0;
 //BA.debugLineNum = 389;BA.debugLine="Sub Button2_Click";
 //BA.debugLineNum = 391;BA.debugLine="If spServo.SelectedItem=\"PULSE AQUI\" Then";
if ((mostCurrent._spservo.getSelectedItem()).equals("PULSE AQUI")) { 
 //BA.debugLineNum = 392;BA.debugLine="ToastMessageShow(\"VALOR NO VALIDO\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("VALOR NO VALIDO",anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 394;BA.debugLine="Try";
try { //BA.debugLineNum = 398;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 399;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 400;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 401;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 402;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 403;BA.debugLine="If posicion_mod<5 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<5) { 
 //BA.debugLineNum = 405;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 406;BA.debugLine="aux_valor_giro_inicio=\"00\"&giro";
_aux_valor_giro_inicio = "00"+mostCurrent._giro;
 //BA.debugLineNum = 407;BA.debugLine="lista1.set(posicion_mod,aux_valor_giro_inicio)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(_aux_valor_giro_inicio));
 };
 //BA.debugLineNum = 410;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 411;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 412;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 413;BA.debugLine="lista1.set(posicion_mod,aux_valor_giro_inicio)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(_aux_valor_giro_inicio));
 };
 };
 //BA.debugLineNum = 417;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 418;BA.debugLine="lista1.set(posicion_mod,giro)";
mostCurrent._lista1.Set((int)(Double.parseDouble(mostCurrent._posicion_mod)),(Object)(mostCurrent._giro));
 };
 //BA.debugLineNum = 421;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 422;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 423;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 424;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 425;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 426;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 427;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 428;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 435;BA.debugLine="If posicion_mod<20 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<20) { 
 //BA.debugLineNum = 436;BA.debugLine="If posicion_mod>=10 Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=10) { 
 //BA.debugLineNum = 437;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 438;BA.debugLine="pos_valor=(pos_mod_aux*4)+10";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+10);
 //BA.debugLineNum = 439;BA.debugLine="ToastMessageShow(\"Linea: \"&posicion_mod&\" Unidad:";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Linea: "+mostCurrent._posicion_mod+" Unidad: "+_pos_mod_aux+" Valor en lista: "+BA.NumberToString(_pos_valor),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 441;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 442;BA.debugLine="aux_valor_tiempo=\"00\"&tiempo";
_aux_valor_tiempo = "00"+mostCurrent._tiempo;
 //BA.debugLineNum = 443;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 446;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 447;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 448;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 450;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 454;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 457;BA.debugLine="lista1.Set(pos_valor,tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(mostCurrent._tiempo));
 };
 //BA.debugLineNum = 461;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 462;BA.debugLine="lista1.set(pos_valor+1,\"0\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("0"));
 };
 //BA.debugLineNum = 464;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 465;BA.debugLine="lista1.set(pos_valor+1,\"1\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("1"));
 };
 //BA.debugLineNum = 467;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 468;BA.debugLine="lista1.set(pos_valor+1,\"0\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("0"));
 };
 //BA.debugLineNum = 471;BA.debugLine="lista1.set(pos_valor+2,servo)";
mostCurrent._lista1.Set((int) (_pos_valor+2),(Object)(mostCurrent._servo));
 //BA.debugLineNum = 474;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 475;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 476;BA.debugLine="lista1.set(pos_valor+3,aux_valor_giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 479;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 480;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 481;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 482;BA.debugLine="lista1.set(pos_valor+3,aux_valor_giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 486;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 487;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 };
 };
 //BA.debugLineNum = 493;BA.debugLine="mod_datos(20,30,50)";
_mod_datos((int) (20),(int) (30),(int) (50));
 //BA.debugLineNum = 495;BA.debugLine="mod_datos(30,40,90)";
_mod_datos((int) (30),(int) (40),(int) (90));
 //BA.debugLineNum = 497;BA.debugLine="mod_datos(40,50,130)";
_mod_datos((int) (40),(int) (50),(int) (130));
 //BA.debugLineNum = 499;BA.debugLine="mod_datos(50,60,170)";
_mod_datos((int) (50),(int) (60),(int) (170));
 //BA.debugLineNum = 507;BA.debugLine="File.WriteList(File.DirRootExternal,\"Modelo_F1C_\"";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 508;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 513;BA.debugLine="For i=0 To 4";
{
final int step82 = 1;
final int limit82 = (int) (4);
for (_i = (int) (0) ; (step82 > 0 && _i <= limit82) || (step82 < 0 && _i >= limit82); _i = ((int)(0 + _i + step82)) ) {
 //BA.debugLineNum = 514;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 515;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&(i+1)&\"   Giro:";
mostCurrent._lvdatos.AddSingleLine("Servo "+BA.NumberToString((_i+1))+"   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get(_i))+"  INICIO");
 }
};
 //BA.debugLineNum = 518;BA.debugLine="For i=5 To 9";
{
final int step86 = 1;
final int limit86 = (int) (9);
for (_i = (int) (5) ; (step86 > 0 && _i <= limit86) || (step86 < 0 && _i >= limit86); _i = ((int)(0 + _i + step86)) ) {
 //BA.debugLineNum = 519;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 520;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&(i-4)&\"   Giro:";
mostCurrent._lvdatos.AddSingleLine("Servo "+BA.NumberToString((_i-4))+"   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get(_i))+"  SOS");
 }
};
 //BA.debugLineNum = 523;BA.debugLine="For i=10 To lista1.Size-1 Step 4";
{
final int step90 = (int) (4);
final int limit90 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (10) ; (step90 > 0 && _i <= limit90) || (step90 < 0 && _i >= limit90); _i = ((int)(0 + _i + step90)) ) {
 //BA.debugLineNum = 524;BA.debugLine="tiempo_aux=lista1.Get(i)";
mostCurrent._tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get(_i));
 //BA.debugLineNum = 525;BA.debugLine="tipo_tiempo_aux=lista1.Get(i+1)";
mostCurrent._tipo_tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+1)));
 //BA.debugLineNum = 526;BA.debugLine="servo_aux=lista1.Get(i+2)";
mostCurrent._servo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+2)));
 //BA.debugLineNum = 527;BA.debugLine="giro_aux=lista1.Get(i+3)";
mostCurrent._giro_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+3)));
 //BA.debugLineNum = 528;BA.debugLine="If tipo_tiempo_aux=\"0\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("0")) { 
 //BA.debugLineNum = 529;BA.debugLine="tipo_tiempo_aux=\"DECIMAS\"";
mostCurrent._tipo_tiempo_aux = "DECIMAS";
 };
 //BA.debugLineNum = 532;BA.debugLine="If tipo_tiempo_aux=\"1\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("1")) { 
 //BA.debugLineNum = 533;BA.debugLine="tipo_tiempo_aux=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo_aux = "SEGUNDOS";
 };
 //BA.debugLineNum = 536;BA.debugLine="If tipo_tiempo_aux=\"2\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("2")) { 
 //BA.debugLineNum = 537;BA.debugLine="tipo_tiempo_aux=\"MINUTOS\"";
mostCurrent._tipo_tiempo_aux = "MINUTOS";
 };
 //BA.debugLineNum = 539;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 540;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&servo_aux&\"   G";
mostCurrent._lvdatos.AddSingleLine("Servo "+mostCurrent._servo_aux+"   Giro: "+mostCurrent._giro_aux+"    Tiempo: "+mostCurrent._tiempo_aux+"  Tipo tiempo: "+mostCurrent._tipo_tiempo);
 }
};
 //BA.debugLineNum = 544;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 545;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 546;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 547;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 550;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 551;BA.debugLine="spServo.SelectedIndex=0";
mostCurrent._spservo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 552;BA.debugLine="etGiro.Text=\"\"";
mostCurrent._etgiro.setText((Object)(""));
 //BA.debugLineNum = 553;BA.debugLine="etTiempo.Text=\"\"";
mostCurrent._ettiempo.setText((Object)(""));
 //BA.debugLineNum = 554;BA.debugLine="spTiempo.SelectedIndex=0";
mostCurrent._sptiempo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 555;BA.debugLine="spServo.Background=BC1";
mostCurrent._spservo.setBackground((android.graphics.drawable.Drawable)(mostCurrent._bc1.getObject()));
 } 
       catch (Exception e367) {
			processBA.setLastException(e367); };
 //BA.debugLineNum = 559;BA.debugLine="End Sub";
return "";
}
public static String  _datos_lv() throws Exception{
int _i = 0;
 //BA.debugLineNum = 910;BA.debugLine="Sub datos_lv";
 //BA.debugLineNum = 912;BA.debugLine="For i=0 To 4";
{
final int step1 = 1;
final int limit1 = (int) (4);
for (_i = (int) (0) ; (step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1); _i = ((int)(0 + _i + step1)) ) {
 //BA.debugLineNum = 913;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 914;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&(i+1)&\"   Giro: \"&l";
mostCurrent._lvdatos.AddSingleLine("Servo "+BA.NumberToString((_i+1))+"   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get(_i))+"  INICIO");
 }
};
 //BA.debugLineNum = 917;BA.debugLine="For i=5 To 9";
{
final int step5 = 1;
final int limit5 = (int) (9);
for (_i = (int) (5) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 918;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Color";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 920;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&(i-4)&\"   Giro: \"&l";
mostCurrent._lvdatos.AddSingleLine("Servo "+BA.NumberToString((_i-4))+"   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get(_i))+"  SOS");
 }
};
 //BA.debugLineNum = 926;BA.debugLine="End Sub";
return "";
}
public static String  _elegir_modelo() throws Exception{
int _valor = 0;
 //BA.debugLineNum = 168;BA.debugLine="Sub elegir_modelo";
 //BA.debugLineNum = 169;BA.debugLine="Dim valor As Int";
_valor = 0;
 //BA.debugLineNum = 170;BA.debugLine="dial.Input=\"\"";
mostCurrent._dial.setInput("");
 //BA.debugLineNum = 171;BA.debugLine="valor=	dial.Show(\"\",\"NOMBRE MODELO\",\"Aceptar\",\"\",\"";
_valor = mostCurrent._dial.Show("","NOMBRE MODELO","Aceptar","","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 172;BA.debugLine="ToastMessageShow(valor,False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.NumberToString(_valor),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 175;BA.debugLine="If dial.Response=-1 Then";
if (mostCurrent._dial.getResponse()==-1) { 
 //BA.debugLineNum = 176;BA.debugLine="ToastMessageShow(\"ok\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("ok",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
public static String  _etfichero_enterpressed() throws Exception{
int _i = 0;
 //BA.debugLineNum = 576;BA.debugLine="Sub etFichero_EnterPressed";
 //BA.debugLineNum = 577;BA.debugLine="If etFichero.Text=\"\" Then";
if ((mostCurrent._etfichero.getText()).equals("")) { 
 //BA.debugLineNum = 578;BA.debugLine="ToastMessageShow(\"error\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("error",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 583;BA.debugLine="NOMBRE_FICHERO=etFichero.Text & \" \" & config";
mostCurrent._nombre_fichero = mostCurrent._etfichero.getText()+" "+mostCurrent._config;
 //BA.debugLineNum = 585;BA.debugLine="If File.Exists(File.DirRootExternal,\"Modelo_F1C_";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"Modelo_F1C_"+mostCurrent._nombre_fichero)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 588;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 589;BA.debugLine="lista1=File.ReadList(File.DirRootExternal,\"Modelo";
mostCurrent._lista1 = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"Modelo_F1C_"+mostCurrent._nombre_fichero);
 //BA.debugLineNum = 590;BA.debugLine="For i=0 To 4";
{
final int step8 = 1;
final int limit8 = (int) (4);
for (_i = (int) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((int)(0 + _i + step8)) ) {
 //BA.debugLineNum = 591;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 592;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&(i+1)&\"   Giro:";
mostCurrent._lvdatos.AddSingleLine("Servo "+BA.NumberToString((_i+1))+"   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get(_i))+"  INICIO");
 }
};
 //BA.debugLineNum = 595;BA.debugLine="For i=5 To 9";
{
final int step12 = 1;
final int limit12 = (int) (9);
for (_i = (int) (5) ; (step12 > 0 && _i <= limit12) || (step12 < 0 && _i >= limit12); _i = ((int)(0 + _i + step12)) ) {
 //BA.debugLineNum = 596;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 597;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&(i-4)&\"   Giro:";
mostCurrent._lvdatos.AddSingleLine("Servo "+BA.NumberToString((_i-4))+"   Giro: "+BA.ObjectToString(mostCurrent._lista1.Get(_i))+"  SOS");
 }
};
 //BA.debugLineNum = 600;BA.debugLine="For i=10 To lista1.Size-1 Step 4";
{
final int step16 = (int) (4);
final int limit16 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (10) ; (step16 > 0 && _i <= limit16) || (step16 < 0 && _i >= limit16); _i = ((int)(0 + _i + step16)) ) {
 //BA.debugLineNum = 601;BA.debugLine="tiempo_aux=lista1.Get(i)";
mostCurrent._tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get(_i));
 //BA.debugLineNum = 602;BA.debugLine="tipo_tiempo_aux=lista1.Get(i+1)";
mostCurrent._tipo_tiempo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+1)));
 //BA.debugLineNum = 603;BA.debugLine="servo_aux=lista1.Get(i+2)";
mostCurrent._servo_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+2)));
 //BA.debugLineNum = 604;BA.debugLine="giro_aux=lista1.Get(i+3)";
mostCurrent._giro_aux = BA.ObjectToString(mostCurrent._lista1.Get((int) (_i+3)));
 //BA.debugLineNum = 605;BA.debugLine="If tipo_tiempo_aux=\"0\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("0")) { 
 //BA.debugLineNum = 606;BA.debugLine="tipo_tiempo_aux=\"DECIMAS\"";
mostCurrent._tipo_tiempo_aux = "DECIMAS";
 };
 //BA.debugLineNum = 609;BA.debugLine="If tipo_tiempo_aux=\"1\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("1")) { 
 //BA.debugLineNum = 610;BA.debugLine="tipo_tiempo_aux=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo_aux = "SEGUNDOS";
 };
 //BA.debugLineNum = 613;BA.debugLine="If tipo_tiempo_aux=\"2\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("2")) { 
 //BA.debugLineNum = 614;BA.debugLine="tipo_tiempo_aux=\"MINUTOS\"";
mostCurrent._tipo_tiempo_aux = "MINUTOS";
 };
 //BA.debugLineNum = 616;BA.debugLine="lvDatos.SingleLineLayout.Label.TextColor=Co";
mostCurrent._lvdatos.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 617;BA.debugLine="lvDatos.AddSingleLine(\"Servo \"&servo_aux&\"   G";
mostCurrent._lvdatos.AddSingleLine("Servo "+mostCurrent._servo_aux+"   Giro: "+mostCurrent._giro_aux+"    Tiempo: "+mostCurrent._tiempo_aux+"  Tipo tiempo: "+mostCurrent._tipo_tiempo_aux);
 }
};
 //BA.debugLineNum = 620;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 621;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 622;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 623;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 624;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 625;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 626;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 627;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 628;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 629;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 630;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 631;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 632;BA.debugLine="Button1.Enabled=True";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 633;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 634;BA.debugLine="contador_lineas=12";
_contador_lineas = (int) (12);
 }else {
 //BA.debugLineNum = 637;BA.debugLine="ToastMessageShow(\"no existe\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("no existe",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 639;BA.debugLine="lbModelo.Text=NOMBRE_FICHERO";
mostCurrent._lbmodelo.setText((Object)(mostCurrent._nombre_fichero));
 //BA.debugLineNum = 640;BA.debugLine="lvDatos.Clear";
mostCurrent._lvdatos.Clear();
 //BA.debugLineNum = 641;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 642;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 643;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 644;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 645;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 646;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 647;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 648;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 649;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 651;BA.debugLine="btOK.Enabled=True";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 652;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 653;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 654;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 655;BA.debugLine="Button2.Visible=False";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 656;BA.debugLine="Button2.Enabled=False";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 657;BA.debugLine="btOK.Visible=True";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 659;BA.debugLine="File.WriteList(File.DirRootExternal,\"Modelo_F1C";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"Modelo_F1C_"+mostCurrent._nombre_fichero,mostCurrent._lista1);
 //BA.debugLineNum = 660;BA.debugLine="contador_lineas=1";
_contador_lineas = (int) (1);
 };
 };
 //BA.debugLineNum = 664;BA.debugLine="End Sub";
return "";
}
public static String  _etgiro_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 571;BA.debugLine="Sub etGiro_TextChanged (Old As String, New As Stri";
 //BA.debugLineNum = 572;BA.debugLine="giro=New";
mostCurrent._giro = _new;
 //BA.debugLineNum = 573;BA.debugLine="End Sub";
return "";
}
public static String  _ettiempo_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 566;BA.debugLine="Sub etTiempo_TextChanged (Old As String, New As St";
 //BA.debugLineNum = 567;BA.debugLine="tiempo=New";
mostCurrent._tiempo = _new;
 //BA.debugLineNum = 568;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
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
 //BA.debugLineNum = 50;BA.debugLine="Dim lista1 As List";
mostCurrent._lista1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 51;BA.debugLine="Dim contador_lineas As Int";
_contador_lineas = 0;
 //BA.debugLineNum = 52;BA.debugLine="Dim servo As String";
mostCurrent._servo = "";
 //BA.debugLineNum = 53;BA.debugLine="Dim giro As String";
mostCurrent._giro = "";
 //BA.debugLineNum = 54;BA.debugLine="Dim tiempo As String";
mostCurrent._tiempo = "";
 //BA.debugLineNum = 55;BA.debugLine="Dim tipo_tiempo As String";
mostCurrent._tipo_tiempo = "";
 //BA.debugLineNum = 57;BA.debugLine="Dim servo_lv As String";
mostCurrent._servo_lv = "";
 //BA.debugLineNum = 58;BA.debugLine="Dim tiempo_lv As String";
mostCurrent._tiempo_lv = "";
 //BA.debugLineNum = 59;BA.debugLine="Dim tipo_tiempo_lv As String";
mostCurrent._tipo_tiempo_lv = "";
 //BA.debugLineNum = 60;BA.debugLine="Dim giro_lv As String";
mostCurrent._giro_lv = "";
 //BA.debugLineNum = 63;BA.debugLine="Dim tiempo_aux As String";
mostCurrent._tiempo_aux = "";
 //BA.debugLineNum = 64;BA.debugLine="Dim tipo_tiempo_aux As String";
mostCurrent._tipo_tiempo_aux = "";
 //BA.debugLineNum = 65;BA.debugLine="Dim giro_aux As String";
mostCurrent._giro_aux = "";
 //BA.debugLineNum = 66;BA.debugLine="Dim servo_aux As String";
mostCurrent._servo_aux = "";
 //BA.debugLineNum = 69;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Private lbNumeroServo As Label";
mostCurrent._lbnumeroservo = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Private lbModelo As Label";
mostCurrent._lbmodelo = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Private Label10 As Label";
mostCurrent._label10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Dim posicion_mod As String";
mostCurrent._posicion_mod = "";
 //BA.debugLineNum = 77;BA.debugLine="Private Button2 As Button";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 80;BA.debugLine="Dim valor_inicial As Int";
_valor_inicial = 0;
 //BA.debugLineNum = 81;BA.debugLine="Dim valor_final As Int";
_valor_final = 0;
 //BA.debugLineNum = 82;BA.debugLine="Dim numero_suma As Int";
_numero_suma = 0;
 //BA.debugLineNum = 83;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 84;BA.debugLine="Dim BC As ColorDrawable";
mostCurrent._bc = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 85;BA.debugLine="Dim BC1 As ColorDrawable";
mostCurrent._bc1 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 86;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 87;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 88;BA.debugLine="Private Panel3 As Panel";
mostCurrent._panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 89;BA.debugLine="Private Panel4 As Panel";
mostCurrent._panel4 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 90;BA.debugLine="Dim spconfig1 As Spinner";
mostCurrent._spconfig1 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 91;BA.debugLine="Private btComenzar As Button";
mostCurrent._btcomenzar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 92;BA.debugLine="Private btSalir As Button";
mostCurrent._btsalir = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static String  _initpanel(anywheresoftware.b4a.objects.PanelWrapper _pnl,float _borderwidth,int _fillcolor,int _bordercolor) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rec = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas1 = null;
float _borderwidth_2 = 0f;
 //BA.debugLineNum = 1018;BA.debugLine="Sub InitPanel(pnl As Panel,BorderWidth As Float, F";
 //BA.debugLineNum = 1019;BA.debugLine="Dim Rec As Rect";
_rec = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 1020;BA.debugLine="Dim Canvas1 As Canvas";
_canvas1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 1021;BA.debugLine="Dim BorderWidth_2 As Float";
_borderwidth_2 = 0f;
 //BA.debugLineNum = 1023;BA.debugLine="BorderWidth_2=BorderWidth/2";
_borderwidth_2 = (float) (_borderwidth/(double)2);
 //BA.debugLineNum = 1024;BA.debugLine="Rec.Initialize(BorderWidth_2,BorderWidth_2,pnl.Wi";
_rec.Initialize((int) (_borderwidth_2),(int) (_borderwidth_2),(int) (_pnl.getWidth()-_borderwidth_2),(int) (_pnl.getHeight()-_borderwidth_2));
 //BA.debugLineNum = 1025;BA.debugLine="Canvas1.Initialize(pnl)";
_canvas1.Initialize((android.view.View)(_pnl.getObject()));
 //BA.debugLineNum = 1026;BA.debugLine="Canvas1.DrawRect(Rec,FillColor,True,FillColor)";
_canvas1.DrawRect((android.graphics.Rect)(_rec.getObject()),_fillcolor,anywheresoftware.b4a.keywords.Common.True,(float) (_fillcolor));
 //BA.debugLineNum = 1027;BA.debugLine="Canvas1.DrawRect(Rec,BorderColor,False,BorderWidt";
_canvas1.DrawRect((android.graphics.Rect)(_rec.getObject()),_bordercolor,anywheresoftware.b4a.keywords.Common.False,_borderwidth);
 //BA.debugLineNum = 1028;BA.debugLine="End Sub";
return "";
}
public static String  _lbmodelo_longclick() throws Exception{
 //BA.debugLineNum = 929;BA.debugLine="Sub lbModelo_LongClick";
 //BA.debugLineNum = 930;BA.debugLine="elegir_modelo";
_elegir_modelo();
 //BA.debugLineNum = 933;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
com.delozoya.Consola.Completa.customlistview _lv = null;
 //BA.debugLineNum = 1009;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 1010;BA.debugLine="Dim lv As CustomListView";
_lv = new com.delozoya.Consola.Completa.customlistview();
 //BA.debugLineNum = 1011;BA.debugLine="ListView1.SetSelection(Position)";
mostCurrent._listview1.SetSelection(_position);
 //BA.debugLineNum = 1012;BA.debugLine="lvDatos.SetSelection(Position)";
mostCurrent._lvdatos.SetSelection(_position);
 //BA.debugLineNum = 1014;BA.debugLine="End Sub";
return "";
}
public static String  _log_lista() throws Exception{
int _i = 0;
 //BA.debugLineNum = 900;BA.debugLine="Sub log_lista";
 //BA.debugLineNum = 901;BA.debugLine="For i=0 To lista1.Size- 1";
{
final int step1 = 1;
final int limit1 = (int) (mostCurrent._lista1.getSize()-1);
for (_i = (int) (0) ; (step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1); _i = ((int)(0 + _i + step1)) ) {
 //BA.debugLineNum = 903;BA.debugLine="Log(lista1.Get(i))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lista1.Get(_i)));
 }
};
 //BA.debugLineNum = 908;BA.debugLine="End Sub";
return "";
}
public static String  _lvdatos_itemclick(int _position,Object _value) throws Exception{
String _str = "";
 //BA.debugLineNum = 213;BA.debugLine="Sub lvDatos_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 214;BA.debugLine="Button2.Enabled=True";
mostCurrent._button2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 215;BA.debugLine="Button2.Visible=True";
mostCurrent._button2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 216;BA.debugLine="Button1.Enabled=False";
mostCurrent._button1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 217;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 218;BA.debugLine="btOK.Enabled=False";
mostCurrent._btok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 219;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 220;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 222;BA.debugLine="If Position<=4 Then";
if (_position<=4) { 
 //BA.debugLineNum = 223;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 224;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 225;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 226;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 227;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 228;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 229;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 230;BA.debugLine="Label8.Visible=True";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 234;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 235;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 236;BA.debugLine="servo_lv=str.SubString2(6,7)";
mostCurrent._servo_lv = _str.substring((int) (6),(int) (7));
 //BA.debugLineNum = 237;BA.debugLine="giro_lv=str.SubString2(16,19)";
mostCurrent._giro_lv = _str.substring((int) (16),(int) (19));
 //BA.debugLineNum = 238;BA.debugLine="lbNumeroServo.text=servo_lv";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo_lv));
 //BA.debugLineNum = 239;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 240;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 243;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 244;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 245;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 250;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 252;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 259;BA.debugLine="ToastMessageShow(\"giro \"&giro_lv,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("giro "+mostCurrent._giro_lv,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 260;BA.debugLine="ToastMessageShow(\"servo \"&servo_lv,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("servo "+mostCurrent._servo_lv,anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 264;BA.debugLine="If Position>=5 Then";
if (_position>=5) { 
 //BA.debugLineNum = 265;BA.debugLine="If Position<=9 Then";
if (_position<=9) { 
 //BA.debugLineNum = 266;BA.debugLine="spServo.Visible=False";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 267;BA.debugLine="spTiempo.Visible=False";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 268;BA.debugLine="Label2.Visible=False";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 269;BA.debugLine="Panel3.Visible=False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 270;BA.debugLine="Label4.Visible=False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 271;BA.debugLine="Panel4.Visible=False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 272;BA.debugLine="etTiempo.Visible=False";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 273;BA.debugLine="Label9.Visible=True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 276;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 277;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 278;BA.debugLine="servo_lv=str.SubString2(6,7)";
mostCurrent._servo_lv = _str.substring((int) (6),(int) (7));
 //BA.debugLineNum = 279;BA.debugLine="giro_lv=str.SubString2(16,19)";
mostCurrent._giro_lv = _str.substring((int) (16),(int) (19));
 //BA.debugLineNum = 282;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 283;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 286;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 287;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 288;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 293;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 295;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 300;BA.debugLine="ToastMessageShow(\"giro \"&giro_lv,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("giro "+mostCurrent._giro_lv,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 301;BA.debugLine="ToastMessageShow(\"servo \"&servo_lv,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("servo "+mostCurrent._servo_lv,anywheresoftware.b4a.keywords.Common.True);
 };
 };
 //BA.debugLineNum = 304;BA.debugLine="If Position>=10 Then";
if (_position>=10) { 
 //BA.debugLineNum = 305;BA.debugLine="spServo.Visible=True";
mostCurrent._spservo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 306;BA.debugLine="spTiempo.Visible=True";
mostCurrent._sptiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 307;BA.debugLine="Label2.Visible=True";
mostCurrent._label2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 308;BA.debugLine="Panel3.Visible=True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 309;BA.debugLine="Label4.Visible=True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 310;BA.debugLine="Panel4.Visible=True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 311;BA.debugLine="etTiempo.Visible=True";
mostCurrent._ettiempo.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 312;BA.debugLine="Label9.Visible=False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 313;BA.debugLine="Label8.Visible=False";
mostCurrent._label8.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 314;BA.debugLine="lbNumeroServo.Text=\"\"";
mostCurrent._lbnumeroservo.setText((Object)(""));
 //BA.debugLineNum = 317;BA.debugLine="posicion_mod=Position";
mostCurrent._posicion_mod = BA.NumberToString(_position);
 //BA.debugLineNum = 318;BA.debugLine="str=Value";
_str = BA.ObjectToString(_value);
 //BA.debugLineNum = 319;BA.debugLine="ToastMessageShow(str,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(_str,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 320;BA.debugLine="ToastMessageShow(str.SubString2(30,34),True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(_str.substring((int) (30),(int) (34)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 321;BA.debugLine="ToastMessageShow(str.SubString2(49,50),True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(_str.substring((int) (49),(int) (50)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 323;BA.debugLine="tiempo_lv =str.SubString2(30,34)";
mostCurrent._tiempo_lv = _str.substring((int) (30),(int) (34));
 //BA.debugLineNum = 324;BA.debugLine="etTiempo.Text=tiempo_lv";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv));
 //BA.debugLineNum = 326;BA.debugLine="If tiempo_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))<=9) { 
 //BA.debugLineNum = 327;BA.debugLine="etTiempo.Text=tiempo_lv.SubString(2)";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 330;BA.debugLine="If tiempo_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))<=99) { 
 //BA.debugLineNum = 331;BA.debugLine="If tiempo_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))>9) { 
 //BA.debugLineNum = 332;BA.debugLine="etTiempo.Text=tiempo_lv.SubString2(1,3)";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 337;BA.debugLine="If tiempo_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo_lv))>99) { 
 //BA.debugLineNum = 339;BA.debugLine="etTiempo.Text=tiempo_lv";
mostCurrent._ettiempo.setText((Object)(mostCurrent._tiempo_lv));
 };
 //BA.debugLineNum = 344;BA.debugLine="servo_lv=str.SubString2(6,7)";
mostCurrent._servo_lv = _str.substring((int) (6),(int) (7));
 //BA.debugLineNum = 345;BA.debugLine="lbNumeroServo.Text=servo_lv";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo_lv));
 //BA.debugLineNum = 346;BA.debugLine="spServo.SelectedIndex=servo_lv";
mostCurrent._spservo.setSelectedIndex((int)(Double.parseDouble(mostCurrent._servo_lv)));
 //BA.debugLineNum = 349;BA.debugLine="giro_lv=str.SubString2(16,19)";
mostCurrent._giro_lv = _str.substring((int) (16),(int) (19));
 //BA.debugLineNum = 350;BA.debugLine="If giro_lv<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=9) { 
 //BA.debugLineNum = 351;BA.debugLine="etGiro.Text=giro_lv.SubString(2)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (2))));
 };
 //BA.debugLineNum = 354;BA.debugLine="If giro_lv <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))<=99) { 
 //BA.debugLineNum = 355;BA.debugLine="If giro_lv>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>9) { 
 //BA.debugLineNum = 356;BA.debugLine="etGiro.Text=giro_lv.SubString2(1,3)";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv.substring((int) (1),(int) (3))));
 };
 };
 //BA.debugLineNum = 361;BA.debugLine="If giro_lv >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro_lv))>99) { 
 //BA.debugLineNum = 363;BA.debugLine="etGiro.Text=giro_lv";
mostCurrent._etgiro.setText((Object)(mostCurrent._giro_lv));
 };
 //BA.debugLineNum = 367;BA.debugLine="tipo_tiempo_aux=str.SubString2(49,50)";
mostCurrent._tipo_tiempo_aux = _str.substring((int) (49),(int) (50));
 //BA.debugLineNum = 368;BA.debugLine="If tipo_tiempo_aux=\"D\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("D")) { 
 //BA.debugLineNum = 369;BA.debugLine="tipo_tiempo=\"DECIMAS\"";
mostCurrent._tipo_tiempo = "DECIMAS";
 };
 //BA.debugLineNum = 372;BA.debugLine="If tipo_tiempo_aux=\"S\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("S")) { 
 //BA.debugLineNum = 373;BA.debugLine="tipo_tiempo=\"SEGUNDOS\"";
mostCurrent._tipo_tiempo = "SEGUNDOS";
 };
 //BA.debugLineNum = 376;BA.debugLine="If tipo_tiempo_aux=\"M\" Then";
if ((mostCurrent._tipo_tiempo_aux).equals("M")) { 
 //BA.debugLineNum = 377;BA.debugLine="tipo_tiempo=\"MINUTOS\"";
mostCurrent._tipo_tiempo = "MINUTOS";
 };
 };
 //BA.debugLineNum = 382;BA.debugLine="ToastMessageShow(Value,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 385;BA.debugLine="End Sub";
return "";
}
public static String  _lvdatos_itemlongclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 561;BA.debugLine="Sub lvDatos_ItemLongClick (Position As Int, Value";
 //BA.debugLineNum = 563;BA.debugLine="End Sub";
return "";
}
public static String  _mod_datos(int _valor_ini,int _valor_fin,int _valor_sum) throws Exception{
String _aux_valor_tiempo = "";
String _aux_valor_giro = "";
int _pos_valor = 0;
String _pos_mod_aux = "";
String _aux_valor_giro_inicio = "";
 //BA.debugLineNum = 937;BA.debugLine="Sub mod_datos (valor_ini As Int, valor_fin As Int,";
 //BA.debugLineNum = 938;BA.debugLine="Dim aux_valor_tiempo As String";
_aux_valor_tiempo = "";
 //BA.debugLineNum = 939;BA.debugLine="Dim aux_valor_giro As String";
_aux_valor_giro = "";
 //BA.debugLineNum = 940;BA.debugLine="Dim pos_valor As Int";
_pos_valor = 0;
 //BA.debugLineNum = 941;BA.debugLine="Dim pos_mod_aux As String";
_pos_mod_aux = "";
 //BA.debugLineNum = 942;BA.debugLine="Dim aux_valor_giro_inicio As String";
_aux_valor_giro_inicio = "";
 //BA.debugLineNum = 946;BA.debugLine="If posicion_mod<valor_fin Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))<_valor_fin) { 
 //BA.debugLineNum = 947;BA.debugLine="If posicion_mod>=valor_ini Then";
if ((double)(Double.parseDouble(mostCurrent._posicion_mod))>=_valor_ini) { 
 //BA.debugLineNum = 948;BA.debugLine="pos_mod_aux=posicion_mod.SubString2(1,2)";
_pos_mod_aux = mostCurrent._posicion_mod.substring((int) (1),(int) (2));
 //BA.debugLineNum = 949;BA.debugLine="pos_valor=(pos_mod_aux*4)+valor_sum";
_pos_valor = (int) (((double)(Double.parseDouble(_pos_mod_aux))*4)+_valor_sum);
 //BA.debugLineNum = 950;BA.debugLine="ToastMessageShow(\"Linea: \"&posicion_mod&\" Unidad:";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Linea: "+mostCurrent._posicion_mod+" Unidad: "+_pos_mod_aux+" Valor en lista: "+BA.NumberToString(_pos_valor),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 952;BA.debugLine="If tiempo<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=9) { 
 //BA.debugLineNum = 953;BA.debugLine="aux_valor_tiempo=\"00\"&tiempo";
_aux_valor_tiempo = "00"+mostCurrent._tiempo;
 //BA.debugLineNum = 954;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 //BA.debugLineNum = 957;BA.debugLine="If tiempo <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))<=99) { 
 //BA.debugLineNum = 958;BA.debugLine="If tiempo>9 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>9) { 
 //BA.debugLineNum = 959;BA.debugLine="aux_valor_tiempo=\"0\"&tiempo";
_aux_valor_tiempo = "0"+mostCurrent._tiempo;
 //BA.debugLineNum = 961;BA.debugLine="lista1.Set(pos_valor,aux_valor_tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(_aux_valor_tiempo));
 };
 };
 //BA.debugLineNum = 965;BA.debugLine="If tiempo >99 Then";
if ((double)(Double.parseDouble(mostCurrent._tiempo))>99) { 
 //BA.debugLineNum = 968;BA.debugLine="lista1.Set(pos_valor,tiempo)";
mostCurrent._lista1.Set(_pos_valor,(Object)(mostCurrent._tiempo));
 };
 //BA.debugLineNum = 972;BA.debugLine="If tipo_tiempo=\"DECIMAS\" Then";
if ((mostCurrent._tipo_tiempo).equals("DECIMAS")) { 
 //BA.debugLineNum = 973;BA.debugLine="lista1.set(pos_valor+1,\"0\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("0"));
 };
 //BA.debugLineNum = 975;BA.debugLine="If tipo_tiempo=\"SEGUNDOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("SEGUNDOS")) { 
 //BA.debugLineNum = 976;BA.debugLine="lista1.set(pos_valor+1,\"1\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("1"));
 };
 //BA.debugLineNum = 978;BA.debugLine="If tipo_tiempo=\"MINUTOS\" Then";
if ((mostCurrent._tipo_tiempo).equals("MINUTOS")) { 
 //BA.debugLineNum = 979;BA.debugLine="lista1.set(pos_valor+1,\"0\")";
mostCurrent._lista1.Set((int) (_pos_valor+1),(Object)("0"));
 };
 //BA.debugLineNum = 982;BA.debugLine="lista1.set(pos_valor+2,servo)";
mostCurrent._lista1.Set((int) (_pos_valor+2),(Object)(mostCurrent._servo));
 //BA.debugLineNum = 985;BA.debugLine="If giro<=9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=9) { 
 //BA.debugLineNum = 986;BA.debugLine="aux_valor_giro=\"00\"&giro";
_aux_valor_giro = "00"+mostCurrent._giro;
 //BA.debugLineNum = 987;BA.debugLine="lista1.set(pos_valor+3,aux_valor_giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(_aux_valor_giro));
 };
 //BA.debugLineNum = 990;BA.debugLine="If giro <=99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))<=99) { 
 //BA.debugLineNum = 991;BA.debugLine="If giro>9 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>9) { 
 //BA.debugLineNum = 992;BA.debugLine="aux_valor_giro=\"0\"&giro";
_aux_valor_giro = "0"+mostCurrent._giro;
 //BA.debugLineNum = 993;BA.debugLine="lista1.set(pos_valor+3,aux_valor_giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(_aux_valor_giro));
 };
 };
 //BA.debugLineNum = 997;BA.debugLine="If giro >99 Then";
if ((double)(Double.parseDouble(mostCurrent._giro))>99) { 
 //BA.debugLineNum = 998;BA.debugLine="lista1.set(pos_valor+3,giro)";
mostCurrent._lista1.Set((int) (_pos_valor+3),(Object)(mostCurrent._giro));
 };
 };
 };
 //BA.debugLineNum = 1007;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim flag As Int=0";
_flag = (int) (0);
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _spconfig_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 197;BA.debugLine="Sub spconfig_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 199;BA.debugLine="If Value=\"PULSE AQUI\" Then";
if ((_value).equals((Object)("PULSE AQUI"))) { 
 //BA.debugLineNum = 201;BA.debugLine="btOK.Visible=False";
mostCurrent._btok.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 204;BA.debugLine="config=Value";
mostCurrent._config = BA.ObjectToString(_value);
 //BA.debugLineNum = 205;BA.debugLine="etFichero.Text = dial.Input & \" \" '& config";
mostCurrent._etfichero.setText((Object)(mostCurrent._dial.getInput()+" "));
 //BA.debugLineNum = 206;BA.debugLine="etFichero_EnterPressed";
_etfichero_enterpressed();
 };
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
return "";
}
public static String  _spservo_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Sub spServo_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 188;BA.debugLine="servo=Position";
mostCurrent._servo = BA.NumberToString(_position);
 //BA.debugLineNum = 189;BA.debugLine="lbNumeroServo.Text=servo";
mostCurrent._lbnumeroservo.setText((Object)(mostCurrent._servo));
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _sptiempo_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 888;BA.debugLine="Sub spTiempo_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 890;BA.debugLine="If Value=\"PULSE AQUI\" Then";
if ((_value).equals((Object)("PULSE AQUI"))) { 
 //BA.debugLineNum = 891;BA.debugLine="ToastMessageShow(\"VALOR NO VALIDO\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("VALOR NO VALIDO",anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 893;BA.debugLine="tipo_tiempo=Value";
mostCurrent._tipo_tiempo = BA.ObjectToString(_value);
 };
 //BA.debugLineNum = 896;BA.debugLine="End Sub";
return "";
}
}
