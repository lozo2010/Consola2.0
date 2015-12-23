package com.delozoya.Consola.Completa;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class custommsgbox extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.delozoya.Consola.Completa.custommsgbox");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.delozoya.Consola.Completa.custommsgbox.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public Object _msgmodule = null;
public anywheresoftware.b4a.objects.PanelWrapper _backpanel = null;
public String _msgorientation = "";
public int _msgnumberofbuttons = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _mbicon = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel = null;
public anywheresoftware.b4a.objects.PanelWrapper _shadow = null;
public anywheresoftware.b4a.objects.LabelWrapper _title = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _msgscrollview = null;
public anywheresoftware.b4a.objects.LabelWrapper _message = null;
public anywheresoftware.b4a.objects.PanelWrapper _yesbuttonpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _nobuttonpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _cancelbuttonpanel = null;
public anywheresoftware.b4a.objects.LabelWrapper _yesbuttoncaption = null;
public anywheresoftware.b4a.objects.LabelWrapper _nobuttoncaption = null;
public anywheresoftware.b4a.objects.LabelWrapper _cancelbuttoncaption = null;
public String _msgboxevent = "";
public String _buttonselected = "";
public com.delozoya.Consola.Completa.main _main = null;
public com.delozoya.Consola.Completa.f1a _f1a = null;
public com.delozoya.Consola.Completa.f1b1 _f1b1 = null;
public com.delozoya.Consola.Completa.f1c _f1c = null;
public com.delozoya.Consola.Completa.f1b _f1b = null;
public String  _backpanel_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 328;BA.debugLine="Private Sub BackPanel_Touch(Action As Int, X As Fl";
 //BA.debugLineNum = 330;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private MsgModule As Object";
_msgmodule = new Object();
 //BA.debugLineNum = 10;BA.debugLine="Private BackPanel As Panel";
_backpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private MsgOrientation As String";
_msgorientation = "";
 //BA.debugLineNum = 12;BA.debugLine="Private MsgNumberOfButtons As Int";
_msgnumberofbuttons = 0;
 //BA.debugLineNum = 14;BA.debugLine="Private mbIcon As ImageView";
_mbicon = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Dim Panel As Panel";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private Shadow As Panel";
_shadow = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim Title As Label";
_title = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private MsgScrollView As ScrollView";
_msgscrollview = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim Message As Label";
_message = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim YesButtonPanel As Panel";
_yesbuttonpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim NoButtonPanel As Panel";
_nobuttonpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Dim CancelButtonPanel As Panel";
_cancelbuttonpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Dim YesButtonCaption As Label";
_yesbuttoncaption = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim NoButtonCaption As Label";
_nobuttoncaption = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Dim CancelButtonCaption As Label";
_cancelbuttoncaption = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private MsgBoxEvent As String";
_msgboxevent = "";
 //BA.debugLineNum = 29;BA.debugLine="Dim ButtonSelected As String";
_buttonselected = "";
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _activity,Object _module,String _msgboxname,String _orientation,int _numberofbuttons,double _width,double _height,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _icon) throws Exception{
innerInitialize(_ba);
String _delta = "";
String _rate = "";
String _scale = "";
String _buttonsize = "";
 //BA.debugLineNum = 42;BA.debugLine="Public Sub Initialize(Activity As Activity, Module";
 //BA.debugLineNum = 44;BA.debugLine="MsgModule = Module";
_msgmodule = _module;
 //BA.debugLineNum = 45;BA.debugLine="MsgOrientation = Orientation";
_msgorientation = _orientation;
 //BA.debugLineNum = 46;BA.debugLine="MsgNumberOfButtons = NumberOfButtons";
_msgnumberofbuttons = _numberofbuttons;
 //BA.debugLineNum = 48;BA.debugLine="Delta = ((100%x + 100%y) / (320dip + 480dip";
_delta = BA.NumberToString(((__c.PerXToCurrent((float) (100),ba)+__c.PerYToCurrent((float) (100),ba))/(double)(__c.DipToCurrent((int) (320))+__c.DipToCurrent((int) (480)))-1));
 //BA.debugLineNum = 49;BA.debugLine="Rate = 0.3";
_rate = BA.NumberToString(0.3);
 //BA.debugLineNum = 50;BA.debugLine="Scale = Rate * Delta	+ 1";
_scale = BA.NumberToString((double)(Double.parseDouble(_rate))*(double)(Double.parseDouble(_delta))+1);
 //BA.debugLineNum = 52;BA.debugLine="MsgBoxEvent = MsgBoxName";
_msgboxevent = _msgboxname;
 //BA.debugLineNum = 54;BA.debugLine="BackPanel.Initialize(\"BackPanel\")";
_backpanel.Initialize(ba,"BackPanel");
 //BA.debugLineNum = 56;BA.debugLine="Panel.Initialize(\"\")";
_panel.Initialize(ba,"");
 //BA.debugLineNum = 57;BA.debugLine="Shadow.Initialize(\"\")";
_shadow.Initialize(ba,"");
 //BA.debugLineNum = 58;BA.debugLine="mbIcon.Initialize(\"\")";
_mbicon.Initialize(ba,"");
 //BA.debugLineNum = 59;BA.debugLine="Title.Initialize(\"\")";
_title.Initialize(ba,"");
 //BA.debugLineNum = 60;BA.debugLine="MsgScrollView.Initialize(0dip)";
_msgscrollview.Initialize(ba,__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 61;BA.debugLine="Message.Initialize(\"\")";
_message.Initialize(ba,"");
 //BA.debugLineNum = 62;BA.debugLine="YesButtonPanel.Initialize(\"PressedButton\")";
_yesbuttonpanel.Initialize(ba,"PressedButton");
 //BA.debugLineNum = 63;BA.debugLine="NoButtonPanel.Initialize(\"PressedButton\")";
_nobuttonpanel.Initialize(ba,"PressedButton");
 //BA.debugLineNum = 64;BA.debugLine="CancelButtonPanel.Initialize(\"PressedButton";
_cancelbuttonpanel.Initialize(ba,"PressedButton");
 //BA.debugLineNum = 65;BA.debugLine="YesButtonCaption.Initialize(\"\")";
_yesbuttoncaption.Initialize(ba,"");
 //BA.debugLineNum = 66;BA.debugLine="NoButtonCaption.Initialize(\"\")";
_nobuttoncaption.Initialize(ba,"");
 //BA.debugLineNum = 67;BA.debugLine="CancelButtonCaption.Initialize(\"\")";
_cancelbuttoncaption.Initialize(ba,"");
 //BA.debugLineNum = 69;BA.debugLine="Activity.AddView(BackPanel, 0dip, 0dip, 100";
_activity.AddView((android.view.View)(_backpanel.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 //BA.debugLineNum = 71;BA.debugLine="BackPanel.Color = Colors.ARGB(160, 0, 0, 0)";
_backpanel.setColor(__c.Colors.ARGB((int) (160),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 73;BA.debugLine="BackPanel.AddView(Panel, 0dip, 0dip, 100%x,";
_backpanel.AddView((android.view.View)(_panel.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 //BA.debugLineNum = 75;BA.debugLine="Panel.Width = Width";
_panel.setWidth((int) (_width));
 //BA.debugLineNum = 76;BA.debugLine="Panel.Height = Height";
_panel.setHeight((int) (_height));
 //BA.debugLineNum = 77;BA.debugLine="Panel.Color = Colors.DarkGray";
_panel.setColor(__c.Colors.DarkGray);
 //BA.debugLineNum = 78;BA.debugLine="Panel.Left = (100%x - Panel.Width) / 2";
_panel.setLeft((int) ((__c.PerXToCurrent((float) (100),ba)-_panel.getWidth())/(double)2));
 //BA.debugLineNum = 79;BA.debugLine="Panel.Top = (100%y - Panel.Height) / 2";
_panel.setTop((int) ((__c.PerYToCurrent((float) (100),ba)-_panel.getHeight())/(double)2));
 //BA.debugLineNum = 80;BA.debugLine="BackPanel.AddView(Shadow, Panel.Left + 5dip";
_backpanel.AddView((android.view.View)(_shadow.getObject()),(int) (_panel.getLeft()+__c.DipToCurrent((int) (5))),(int) (_panel.getTop()+__c.DipToCurrent((int) (5))),_panel.getWidth(),_panel.getHeight());
 //BA.debugLineNum = 81;BA.debugLine="Shadow.SendToBack";
_shadow.SendToBack();
 //BA.debugLineNum = 82;BA.debugLine="Panel.AddView(mbIcon, 5dip, 5dip, 50dip, 50";
_panel.AddView((android.view.View)(_mbicon.getObject()),__c.DipToCurrent((int) (5)),__c.DipToCurrent((int) (5)),__c.DipToCurrent((int) (50)),__c.DipToCurrent((int) (50)));
 //BA.debugLineNum = 83;BA.debugLine="Panel.AddView(Title, 60dip, 0dip, Panel.Wid";
_panel.AddView((android.view.View)(_title.getObject()),__c.DipToCurrent((int) (60)),__c.DipToCurrent((int) (0)),_panel.getWidth(),__c.DipToCurrent((int) (60)));
 //BA.debugLineNum = 84;BA.debugLine="Panel.AddView(MsgScrollView, 5dip, 70dip, P";
_panel.AddView((android.view.View)(_msgscrollview.getObject()),__c.DipToCurrent((int) (5)),__c.DipToCurrent((int) (70)),_panel.getWidth(),__c.DipToCurrent((int) (60)));
 //BA.debugLineNum = 86;BA.debugLine="Panel.AddView(YesButtonPanel, 0dip, Panel.H";
_panel.AddView((android.view.View)(_yesbuttonpanel.getObject()),__c.DipToCurrent((int) (0)),(int) (_panel.getHeight()-__c.DipToCurrent((int) (50))),__c.DipToCurrent((int) (50)),__c.DipToCurrent((int) (50)));
 //BA.debugLineNum = 87;BA.debugLine="Panel.AddView(NoButtonPanel, 0dip, Panel.He";
_panel.AddView((android.view.View)(_nobuttonpanel.getObject()),__c.DipToCurrent((int) (0)),(int) (_panel.getHeight()-__c.DipToCurrent((int) (50))),__c.DipToCurrent((int) (50)),__c.DipToCurrent((int) (50)));
 //BA.debugLineNum = 88;BA.debugLine="Panel.AddView(CancelButtonPanel, 0dip, Pane";
_panel.AddView((android.view.View)(_cancelbuttonpanel.getObject()),__c.DipToCurrent((int) (0)),(int) (_panel.getHeight()-__c.DipToCurrent((int) (50))),__c.DipToCurrent((int) (50)),__c.DipToCurrent((int) (50)));
 //BA.debugLineNum = 90;BA.debugLine="MsgScrollView.Panel.AddView(Message, 0dip,";
_msgscrollview.getPanel().AddView((android.view.View)(_message.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),_msgscrollview.getWidth(),_msgscrollview.getHeight());
 //BA.debugLineNum = 92;BA.debugLine="Message.Height = YesButtonPanel.Top - Messa";
_message.setHeight((int) (_yesbuttonpanel.getTop()-_message.getTop()-__c.DipToCurrent((int) (10))));
 //BA.debugLineNum = 93;BA.debugLine="MsgScrollView.Height = YesButtonPanel.Top -";
_msgscrollview.setHeight((int) (_yesbuttonpanel.getTop()-_msgscrollview.getTop()-__c.DipToCurrent((int) (10))));
 //BA.debugLineNum = 94;BA.debugLine="MsgScrollView.Panel.Height = MsgScrollView.";
_msgscrollview.getPanel().setHeight(_msgscrollview.getHeight());
 //BA.debugLineNum = 96;BA.debugLine="If Orientation.ToUpperCase = \"V\" Then";
if ((_orientation.toUpperCase()).equals("V")) { 
 //BA.debugLineNum = 98;BA.debugLine="YesButtonPanel.Top = Panel.Height - (50d";
_yesbuttonpanel.setTop((int) (_panel.getHeight()-(__c.DipToCurrent((int) (50))*_numberofbuttons)));
 //BA.debugLineNum = 99;BA.debugLine="NoButtonPanel.Top = YesButtonPanel.Top +";
_nobuttonpanel.setTop((int) (_yesbuttonpanel.getTop()+__c.DipToCurrent((int) (52))));
 //BA.debugLineNum = 100;BA.debugLine="CancelButtonPanel.Top = NoButtonPanel.To";
_cancelbuttonpanel.setTop((int) (_nobuttonpanel.getTop()+__c.DipToCurrent((int) (52))));
 };
 //BA.debugLineNum = 104;BA.debugLine="If Icon.IsInitialized Then";
if (_icon.IsInitialized()) { 
 //BA.debugLineNum = 106;BA.debugLine="mbIcon.SetBackgroundImage(Icon)";
_mbicon.SetBackgroundImage((android.graphics.Bitmap)(_icon.getObject()));
 }else {
 //BA.debugLineNum = 110;BA.debugLine="Title.Text = Title.Text.Trim";
_title.setText((Object)(_title.getText().trim()));
 //BA.debugLineNum = 111;BA.debugLine="Title.Left = 5dip";
_title.setLeft(__c.DipToCurrent((int) (5)));
 };
 //BA.debugLineNum = 115;BA.debugLine="Title.Width = Panel.Width - Title.Left - 10";
_title.setWidth((int) (_panel.getWidth()-_title.getLeft()-__c.DipToCurrent((int) (10))));
 //BA.debugLineNum = 116;BA.debugLine="Title.Gravity = Gravity.CENTER_VERTICAL";
_title.setGravity(__c.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 117;BA.debugLine="Title.TextColor = Colors.Cyan";
_title.setTextColor(__c.Colors.Cyan);
 //BA.debugLineNum = 118;BA.debugLine="Title.TextSize = Title.TextSize * Scale + 4";
_title.setTextSize((float) (_title.getTextSize()*(double)(Double.parseDouble(_scale))+4));
 //BA.debugLineNum = 120;BA.debugLine="Message.Width = Panel.Width - Message.Left";
_message.setWidth((int) (_panel.getWidth()-_message.getLeft()-__c.DipToCurrent((int) (10))));
 //BA.debugLineNum = 122;BA.debugLine="Message.TextColor = Colors.White";
_message.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 123;BA.debugLine="Message.TextSize = Message.TextSize * Scale";
_message.setTextSize((float) (_message.getTextSize()*(double)(Double.parseDouble(_scale))+1));
 //BA.debugLineNum = 125;BA.debugLine="If Orientation.ToUpperCase = \"V\" Then";
if ((_orientation.toUpperCase()).equals("V")) { 
 //BA.debugLineNum = 127;BA.debugLine="ButtonSize = Panel.Width";
_buttonsize = BA.NumberToString(_panel.getWidth());
 }else {
 //BA.debugLineNum = 131;BA.debugLine="ButtonSize = (Panel.Width / NumberOfButt";
_buttonsize = BA.NumberToString((_panel.getWidth()/(double)_numberofbuttons)-2);
 };
 //BA.debugLineNum = 135;BA.debugLine="YesButtonPanel.Color = Colors.DarkGray";
_yesbuttonpanel.setColor(__c.Colors.DarkGray);
 //BA.debugLineNum = 136;BA.debugLine="YesButtonPanel.Width = ButtonSize";
_yesbuttonpanel.setWidth((int)(Double.parseDouble(_buttonsize)));
 //BA.debugLineNum = 137;BA.debugLine="YesButtonPanel.Left = 0dip";
_yesbuttonpanel.setLeft(__c.DipToCurrent((int) (0)));
 //BA.debugLineNum = 138;BA.debugLine="YesButtonPanel.Tag = \"yes\"";
_yesbuttonpanel.setTag((Object)("yes"));
 //BA.debugLineNum = 139;BA.debugLine="YesButtonPanel.AddView(YesButtonCaption, 0d";
_yesbuttonpanel.AddView((android.view.View)(_yesbuttoncaption.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),_yesbuttonpanel.getWidth(),__c.DipToCurrent((int) (30)));
 //BA.debugLineNum = 141;BA.debugLine="YesButtonCaption.Gravity = Gravity.CENTER_H";
_yesbuttoncaption.setGravity(__c.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 142;BA.debugLine="YesButtonCaption.Top = ((YesButtonPanel.Hei";
_yesbuttoncaption.setTop((int) (((_yesbuttonpanel.getHeight()-_yesbuttoncaption.getHeight())/(double)2)+__c.DipToCurrent((int) (5))));
 //BA.debugLineNum = 143;BA.debugLine="YesButtonCaption.TextColor = Colors.White";
_yesbuttoncaption.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 144;BA.debugLine="YesButtonCaption.Text = \"Yes\"";
_yesbuttoncaption.setText((Object)("Yes"));
 //BA.debugLineNum = 146;BA.debugLine="NoButtonPanel.Color = Colors.DarkGray";
_nobuttonpanel.setColor(__c.Colors.DarkGray);
 //BA.debugLineNum = 147;BA.debugLine="NoButtonPanel.Width = ButtonSize";
_nobuttonpanel.setWidth((int)(Double.parseDouble(_buttonsize)));
 //BA.debugLineNum = 149;BA.debugLine="If Orientation.ToUpperCase = \"V\" Then";
if ((_orientation.toUpperCase()).equals("V")) { 
 //BA.debugLineNum = 151;BA.debugLine="NoButtonPanel.Left = 0dip";
_nobuttonpanel.setLeft(__c.DipToCurrent((int) (0)));
 }else {
 //BA.debugLineNum = 155;BA.debugLine="NoButtonPanel.Left = YesButtonPanel.Widt";
_nobuttonpanel.setLeft((int) (_yesbuttonpanel.getWidth()+__c.DipToCurrent((int) (4))));
 };
 //BA.debugLineNum = 159;BA.debugLine="NoButtonPanel.Tag = \"no\"";
_nobuttonpanel.setTag((Object)("no"));
 //BA.debugLineNum = 160;BA.debugLine="NoButtonPanel.AddView(NoButtonCaption, 0dip";
_nobuttonpanel.AddView((android.view.View)(_nobuttoncaption.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),_nobuttonpanel.getWidth(),__c.DipToCurrent((int) (30)));
 //BA.debugLineNum = 162;BA.debugLine="NoButtonCaption.Gravity = Gravity.CENTER_HO";
_nobuttoncaption.setGravity(__c.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 163;BA.debugLine="NoButtonCaption.Top = ((NoButtonPanel.Heigh";
_nobuttoncaption.setTop((int) (((_nobuttonpanel.getHeight()-_nobuttoncaption.getHeight())/(double)2)+__c.DipToCurrent((int) (5))));
 //BA.debugLineNum = 164;BA.debugLine="NoButtonCaption.TextColor = Colors.White";
_nobuttoncaption.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 165;BA.debugLine="NoButtonCaption.Text = \"No\"";
_nobuttoncaption.setText((Object)("No"));
 //BA.debugLineNum = 167;BA.debugLine="CancelButtonPanel.Color = Colors.DarkGray";
_cancelbuttonpanel.setColor(__c.Colors.DarkGray);
 //BA.debugLineNum = 168;BA.debugLine="CancelButtonPanel.Width = ButtonSize";
_cancelbuttonpanel.setWidth((int)(Double.parseDouble(_buttonsize)));
 //BA.debugLineNum = 170;BA.debugLine="If Orientation.ToUpperCase = \"V\" Then";
if ((_orientation.toUpperCase()).equals("V")) { 
 //BA.debugLineNum = 172;BA.debugLine="CancelButtonPanel.Left = 0dip";
_cancelbuttonpanel.setLeft(__c.DipToCurrent((int) (0)));
 }else {
 //BA.debugLineNum = 176;BA.debugLine="CancelButtonPanel.Left = YesButtonPanel.";
_cancelbuttonpanel.setLeft((int) (_yesbuttonpanel.getWidth()+_nobuttonpanel.getWidth()+__c.DipToCurrent((int) (8))));
 };
 //BA.debugLineNum = 180;BA.debugLine="CancelButtonPanel.Tag = \"cancel\"";
_cancelbuttonpanel.setTag((Object)("cancel"));
 //BA.debugLineNum = 181;BA.debugLine="CancelButtonPanel.AddView(CancelButtonCapti";
_cancelbuttonpanel.AddView((android.view.View)(_cancelbuttoncaption.getObject()),__c.DipToCurrent((int) (0)),__c.DipToCurrent((int) (0)),_cancelbuttonpanel.getWidth(),__c.DipToCurrent((int) (30)));
 //BA.debugLineNum = 183;BA.debugLine="CancelButtonCaption.Gravity = Gravity.CENTE";
_cancelbuttoncaption.setGravity(__c.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 184;BA.debugLine="CancelButtonCaption.Top = ((CancelButtonPan";
_cancelbuttoncaption.setTop((int) (((_cancelbuttonpanel.getHeight()-_cancelbuttoncaption.getHeight())/(double)2)+__c.DipToCurrent((int) (5))));
 //BA.debugLineNum = 185;BA.debugLine="CancelButtonCaption.TextColor = Colors.Whit";
_cancelbuttoncaption.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 186;BA.debugLine="CancelButtonCaption.Text = \"Cancel\"";
_cancelbuttoncaption.setText((Object)("Cancel"));
 //BA.debugLineNum = 188;BA.debugLine="ShowSeparators(Colors.Cyan, Colors.Gray)";
_showseparators(__c.Colors.Cyan,__c.Colors.Gray);
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public String  _pressedbutton_touch(int _action,float _x,float _y) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pressed = null;
 //BA.debugLineNum = 298;BA.debugLine="Private Sub PressedButton_Touch(Action As Int, X A";
 //BA.debugLineNum = 300;BA.debugLine="Dim Pressed As Panel";
_pressed = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 302;BA.debugLine="Pressed = Sender";
_pressed.setObject((android.view.ViewGroup)(__c.Sender(ba)));
 //BA.debugLineNum = 304;BA.debugLine="If Action = 0 Then";
if (_action==0) { 
 //BA.debugLineNum = 306;BA.debugLine="Pressed.Color = Colors.ARGB(255, 0, 140";
_pressed.setColor(__c.Colors.ARGB((int) (255),(int) (0),(int) (140),(int) (140)));
 };
 //BA.debugLineNum = 310;BA.debugLine="If Action = 1 Then";
if (_action==1) { 
 //BA.debugLineNum = 312;BA.debugLine="Pressed.Color = Colors.DarkGray";
_pressed.setColor(__c.Colors.DarkGray);
 //BA.debugLineNum = 314;BA.debugLine="ButtonSelected = Pressed.Tag";
_buttonselected = BA.ObjectToString(_pressed.getTag());
 //BA.debugLineNum = 316;BA.debugLine="BackPanel.Visible = False";
_backpanel.setVisible(__c.False);
 //BA.debugLineNum = 318;BA.debugLine="If SubExists(MsgModule, MsgBoxEvent & \"";
if (__c.SubExists(ba,_msgmodule,_msgboxevent+"_Click")) { 
 //BA.debugLineNum = 320;BA.debugLine="CallSubDelayed(MsgModule, MsgBoxEven";
__c.CallSubDelayed(ba,_msgmodule,_msgboxevent+"_Click");
 };
 };
 //BA.debugLineNum = 326;BA.debugLine="End Sub";
return "";
}
public String  _showmessage(String _boxmessage,String _alignment) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
String _locate = "";
 //BA.debugLineNum = 194;BA.debugLine="Public Sub ShowMessage(BoxMessage As String, Align";
 //BA.debugLineNum = 196;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 198;BA.debugLine="Locate = Gravity.LEFT";
_locate = BA.NumberToString(__c.Gravity.LEFT);
 //BA.debugLineNum = 200;BA.debugLine="Select Case Alignment.ToUpperCase";
switch (BA.switchObjectToInt(_alignment.toUpperCase(),"L","C","R")) {
case 0: {
 //BA.debugLineNum = 204;BA.debugLine="Locate = Gravity.LEFT";
_locate = BA.NumberToString(__c.Gravity.LEFT);
 break; }
case 1: {
 //BA.debugLineNum = 208;BA.debugLine="Locate = Gravity.CENTER";
_locate = BA.NumberToString(__c.Gravity.CENTER);
 break; }
case 2: {
 //BA.debugLineNum = 212;BA.debugLine="Locate = Gravity.RIGHT";
_locate = BA.NumberToString(__c.Gravity.RIGHT);
 break; }
}
;
 //BA.debugLineNum = 216;BA.debugLine="Message.TextColor=Colors.Black";
_message.setTextColor(__c.Colors.Black);
 //BA.debugLineNum = 217;BA.debugLine="Message.Text = BoxMessage";
_message.setText((Object)(_boxmessage));
 //BA.debugLineNum = 218;BA.debugLine="Message.Gravity = Locate";
_message.setGravity((int)(Double.parseDouble(_locate)));
 //BA.debugLineNum = 220;BA.debugLine="MsgScrollView.Panel.Height = su.MeasureMult";
_msgscrollview.getPanel().setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_message.getObject()),_message.getText()));
 //BA.debugLineNum = 221;BA.debugLine="Message.Height = su.MeasureMultilineTextHei";
_message.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_message.getObject()),_message.getText()));
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return "";
}
public String  _showrichmessage(anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString _boxmessage,String _alignment) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
String _locate = "";
 //BA.debugLineNum = 227;BA.debugLine="Public Sub ShowRichMessage(BoxMessage As RichStrin";
 //BA.debugLineNum = 229;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 231;BA.debugLine="Locate = Gravity.LEFT";
_locate = BA.NumberToString(__c.Gravity.LEFT);
 //BA.debugLineNum = 233;BA.debugLine="Select Case Alignment.ToUpperCase";
switch (BA.switchObjectToInt(_alignment.toUpperCase(),"L","C","R")) {
case 0: {
 //BA.debugLineNum = 237;BA.debugLine="Locate = Gravity.LEFT";
_locate = BA.NumberToString(__c.Gravity.LEFT);
 break; }
case 1: {
 //BA.debugLineNum = 241;BA.debugLine="Locate = Gravity.CENTER";
_locate = BA.NumberToString(__c.Gravity.CENTER);
 break; }
case 2: {
 //BA.debugLineNum = 245;BA.debugLine="Locate = Gravity.RIGHT";
_locate = BA.NumberToString(__c.Gravity.RIGHT);
 break; }
}
;
 //BA.debugLineNum = 249;BA.debugLine="Message.Text = BoxMessage";
_message.setText((Object)(_boxmessage.getObject()));
 //BA.debugLineNum = 250;BA.debugLine="Message.Gravity = Locate";
_message.setGravity((int)(Double.parseDouble(_locate)));
 //BA.debugLineNum = 252;BA.debugLine="MsgScrollView.Panel.Height = su.MeasureMult";
_msgscrollview.getPanel().setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_message.getObject()),_message.getText()));
 //BA.debugLineNum = 253;BA.debugLine="Message.Height = su.MeasureMultilineTextHei";
_message.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_message.getObject()),_message.getText()));
 //BA.debugLineNum = 255;BA.debugLine="End Sub";
return "";
}
public String  _showseparators(int _titleseparator,int _buttonseparator) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper _lineseparator = null;
 //BA.debugLineNum = 258;BA.debugLine="Public Sub ShowSeparators(TitleSeparator As Int, B";
 //BA.debugLineNum = 260;BA.debugLine="Dim LineSeparator As Canvas";
_lineseparator = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 262;BA.debugLine="LineSeparator.Initialize(Panel)";
_lineseparator.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 263;BA.debugLine="LineSeparator.DrawLine(0dip, 65dip, 100%x,";
_lineseparator.DrawLine((float) (__c.DipToCurrent((int) (0))),(float) (__c.DipToCurrent((int) (65))),(float) (__c.PerXToCurrent((float) (100),ba)),(float) (__c.DipToCurrent((int) (65))),_titleseparator,(float) (3));
 //BA.debugLineNum = 264;BA.debugLine="LineSeparator.DrawLine(0dip, YesButtonPanel";
_lineseparator.DrawLine((float) (__c.DipToCurrent((int) (0))),(float) (_yesbuttonpanel.getTop()-__c.DipToCurrent((int) (2))),(float) (__c.PerXToCurrent((float) (100),ba)),(float) (_yesbuttonpanel.getTop()-__c.DipToCurrent((int) (2))),_buttonseparator,(float) (1));
 //BA.debugLineNum = 266;BA.debugLine="If MsgNumberOfButtons = 2 AND MsgOrientatio";
if (_msgnumberofbuttons==2 && (_msgorientation.toUpperCase()).equals("V") == false) { 
 //BA.debugLineNum = 268;BA.debugLine="LineSeparator.DrawLine(Panel.Width / 2,";
_lineseparator.DrawLine((float) (_panel.getWidth()/(double)2),(float) (_yesbuttonpanel.getTop()-__c.DipToCurrent((int) (2))),(float) (_panel.getWidth()/(double)2),(float) (_panel.getHeight()),_buttonseparator,(float) (1));
 }else {
 //BA.debugLineNum = 272;BA.debugLine="LineSeparator.DrawLine(0dip, NoButtonPan";
_lineseparator.DrawLine((float) (__c.DipToCurrent((int) (0))),(float) (_nobuttonpanel.getTop()-__c.DipToCurrent((int) (2))),(float) (_panel.getWidth()),(float) (_nobuttonpanel.getTop()-__c.DipToCurrent((int) (2))),_buttonseparator,(float) (1));
 };
 //BA.debugLineNum = 276;BA.debugLine="If MsgNumberOfButtons = 3 AND MsgOrientatio";
if (_msgnumberofbuttons==3 && (_msgorientation.toUpperCase()).equals("V") == false) { 
 //BA.debugLineNum = 278;BA.debugLine="LineSeparator.DrawLine(Panel.Width / 3,";
_lineseparator.DrawLine((float) (_panel.getWidth()/(double)3),(float) (_yesbuttonpanel.getTop()-__c.DipToCurrent((int) (2))),(float) (_panel.getWidth()/(double)3),(float) (_panel.getHeight()),_buttonseparator,(float) (1));
 //BA.debugLineNum = 279;BA.debugLine="LineSeparator.DrawLine(Panel.Width / 3 +";
_lineseparator.DrawLine((float) (_panel.getWidth()/(double)3+_yesbuttonpanel.getWidth()+__c.DipToCurrent((int) (3))),(float) (_yesbuttonpanel.getTop()-__c.DipToCurrent((int) (2))),(float) (_panel.getWidth()/(double)3+_yesbuttonpanel.getWidth()+__c.DipToCurrent((int) (3))),(float) (_panel.getHeight()),_buttonseparator,(float) (1));
 }else {
 //BA.debugLineNum = 283;BA.debugLine="LineSeparator.DrawLine(0dip, CancelButto";
_lineseparator.DrawLine((float) (__c.DipToCurrent((int) (0))),(float) (_cancelbuttonpanel.getTop()-__c.DipToCurrent((int) (2))),(float) (_panel.getWidth()),(float) (_cancelbuttonpanel.getTop()-__c.DipToCurrent((int) (2))),_buttonseparator,(float) (1));
 };
 //BA.debugLineNum = 287;BA.debugLine="Panel.Invalidate";
_panel.Invalidate();
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return "";
}
public String  _showshadow(int _color) throws Exception{
 //BA.debugLineNum = 292;BA.debugLine="Public Sub ShowShadow(Color As Int)";
 //BA.debugLineNum = 294;BA.debugLine="Shadow.Color = Color";
_shadow.setColor(_color);
 //BA.debugLineNum = 296;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
