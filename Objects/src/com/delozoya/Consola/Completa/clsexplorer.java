package com.delozoya.Consola.Completa;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class clsexplorer extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.delozoya.Consola.Completa.clsexplorer");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.delozoya.Consola.Completa.clsexplorer.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public int _bordercolor = 0;
public int _backgroundcolor = 0;
public int _foldertextcolor = 0;
public int _filetextcolor1 = 0;
public int _filetextcolor2 = 0;
public int _dividercolor = 0;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _dialogrect = null;
public boolean _fastscrollenabled = false;
public com.delozoya.Consola.Completa.clsexplorer._typresult _selection = null;
public boolean _ellipsis = false;
public anywheresoftware.b4a.objects.ActivityWrapper _actecran = null;
public String _strchemin = "";
public anywheresoftware.b4a.objects.collections.List _lstfiltre = null;
public boolean _bonlyfolders = false;
public boolean _bvisualiser = false;
public boolean _bmultifolderselection = false;
public boolean _bmultifileselection = false;
public String _strbtnoktxt = "";
public anywheresoftware.b4a.objects.PanelWrapper _pnlmasque = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlcadre = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlfiles = null;
public flm.b4a.scrollview2d.ScrollView2DWrapper _svfichiers = null;
public com.delozoya.Consola.Completa.clschecklist _lstfichiers = null;
public int _itemheight = 0;
public anywheresoftware.b4a.objects.PanelWrapper _pnlvisu = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblvisu = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivvisu = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlcartouche = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edtfilename = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnok = null;
public boolean _waituntilok = false;
public anywheresoftware.b4a.objects.ButtonWrapper _btndelete = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlrange = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnldisplay = null;
public anywheresoftware.b4a.objects.AnimationWrapper _anim = null;
public anywheresoftware.b4a.objects.Timer _timeout = null;
public int _duration = 0;
public int _maxpos = 0;
public boolean _bignoreevent = false;
public boolean _busermovingpnl = false;
public boolean _bwaitforscroll = false;
public anywheresoftware.b4a.objects.IME _ime1 = null;
public String _nombre = "";
public com.delozoya.Consola.Completa.main _main = null;
public com.delozoya.Consola.Completa.f1a _f1a = null;
public com.delozoya.Consola.Completa.f1b1 _f1b1 = null;
public com.delozoya.Consola.Completa.f1c _f1c = null;
public com.delozoya.Consola.Completa.f1b _f1b = null;
public static class _typresult{
public boolean IsInitialized;
public boolean Canceled;
public String ChosenPath;
public String ChosenFile;
public void Initialize() {
IsInitialized = true;
Canceled = false;
ChosenPath = "";
ChosenFile = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public String  _addentry(int _id,String _text1,String _text2,boolean _withcheckbox) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
int _margin = 0;
int _posx = 0;
anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chk = null;
int _largeurlabel = 0;
anywheresoftware.b4a.objects.LabelWrapper _lbl1 = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl2 = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 114;BA.debugLine="Private Sub AddEntry(ID As Int, Text1 As String, T";
 //BA.debugLineNum = 115;BA.debugLine="Dim pnl As Panel: pnl.Initialize(\"\")";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 115;BA.debugLine="Dim pnl As Panel: pnl.Initialize(\"\")";
_pnl.Initialize(ba,"");
 //BA.debugLineNum = 116;BA.debugLine="Dim Margin As Int: Margin = 5dip";
_margin = 0;
 //BA.debugLineNum = 116;BA.debugLine="Dim Margin As Int: Margin = 5dip";
_margin = __c.DipToCurrent((int) (5));
 //BA.debugLineNum = 117;BA.debugLine="Dim PosX As Int: PosX = Margin";
_posx = 0;
 //BA.debugLineNum = 117;BA.debugLine="Dim PosX As Int: PosX = Margin";
_posx = _margin;
 //BA.debugLineNum = 119;BA.debugLine="Dim chk As CheckBox";
_chk = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 120;BA.debugLine="If WithCheckbox Then";
if (_withcheckbox) { 
 //BA.debugLineNum = 121;BA.debugLine="chk.Initialize(\"lstMulti\")";
_chk.Initialize(ba,"lstMulti");
 //BA.debugLineNum = 122;BA.debugLine="pnl.AddView(chk, PosX, 0, 40dip, itemHeight)";
_pnl.AddView((android.view.View)(_chk.getObject()),_posx,(int) (0),__c.DipToCurrent((int) (40)),_itemheight);
 //BA.debugLineNum = 123;BA.debugLine="PosX = chk.Width + chk.Left";
_posx = (int) (_chk.getWidth()+_chk.getLeft());
 };
 //BA.debugLineNum = 126;BA.debugLine="Dim LargeurLabel As Int";
_largeurlabel = 0;
 //BA.debugLineNum = 127;BA.debugLine="LargeurLabel = svFichiers.Width - PosX - Margin";
_largeurlabel = (int) (_svfichiers.getWidth()-_posx-_margin);
 //BA.debugLineNum = 128;BA.debugLine="Dim lbl1 As Label: lbl1.Initialize(\"\")";
_lbl1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 128;BA.debugLine="Dim lbl1 As Label: lbl1.Initialize(\"\")";
_lbl1.Initialize(ba,"");
 //BA.debugLineNum = 129;BA.debugLine="lbl1.Gravity = Gravity.CENTER_VERTICAL";
_lbl1.setGravity(__c.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 130;BA.debugLine="lbl1.Text = Text1";
_lbl1.setText((Object)(_text1));
 //BA.debugLineNum = 131;BA.debugLine="lbl1.TextSize = 18";
_lbl1.setTextSize((float) (18));
 //BA.debugLineNum = 132;BA.debugLine="If Text2 = \"\" Then";
if ((_text2).equals("")) { 
 //BA.debugLineNum = 134;BA.debugLine="lbl1.TextColor = FolderTextColor";
_lbl1.setTextColor(_foldertextcolor);
 //BA.debugLineNum = 135;BA.debugLine="lbl1.Typeface = Typeface.DEFAULT_BOLD";
_lbl1.setTypeface(__c.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 136;BA.debugLine="pnl.AddView(lbl1, PosX, 2dip, LargeurLabel, item";
_pnl.AddView((android.view.View)(_lbl1.getObject()),_posx,__c.DipToCurrent((int) (2)),_largeurlabel,(int) (_itemheight-__c.DipToCurrent((int) (4))));
 }else {
 //BA.debugLineNum = 139;BA.debugLine="lbl1.TextColor = FileTextColor1";
_lbl1.setTextColor(_filetextcolor1);
 //BA.debugLineNum = 140;BA.debugLine="lbl1.Typeface = Typeface.DEFAULT";
_lbl1.setTypeface(__c.Typeface.DEFAULT);
 //BA.debugLineNum = 141;BA.debugLine="pnl.AddView(lbl1, PosX, 2dip, LargeurLabel, Bit.";
_pnl.AddView((android.view.View)(_lbl1.getObject()),_posx,__c.DipToCurrent((int) (2)),_largeurlabel,__c.Bit.ShiftRight(_itemheight,(int) (1)));
 //BA.debugLineNum = 143;BA.debugLine="Dim lbl2 As Label: lbl2.Initialize(\"\")";
_lbl2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 143;BA.debugLine="Dim lbl2 As Label: lbl2.Initialize(\"\")";
_lbl2.Initialize(ba,"");
 //BA.debugLineNum = 144;BA.debugLine="lbl2.Gravity = Gravity.TOP";
_lbl2.setGravity(__c.Gravity.TOP);
 //BA.debugLineNum = 145;BA.debugLine="lbl2.Text = Text2";
_lbl2.setText((Object)(_text2));
 //BA.debugLineNum = 146;BA.debugLine="lbl2.TextColor = FileTextColor2";
_lbl2.setTextColor(_filetextcolor2);
 //BA.debugLineNum = 147;BA.debugLine="lbl2.TextSize = 14";
_lbl2.setTextSize((float) (14));
 //BA.debugLineNum = 148;BA.debugLine="lbl2.Typeface = Typeface.DEFAULT";
_lbl2.setTypeface(__c.Typeface.DEFAULT);
 //BA.debugLineNum = 149;BA.debugLine="pnl.AddView(lbl2, PosX, lbl1.Top + lbl1.Height,";
_pnl.AddView((android.view.View)(_lbl2.getObject()),_posx,(int) (_lbl1.getTop()+_lbl1.getHeight()),_largeurlabel,(int) (_itemheight-_lbl1.getTop()-_lbl1.getHeight()));
 };
 //BA.debugLineNum = 152;BA.debugLine="If Ellipsis Then";
if (_ellipsis) { 
 //BA.debugLineNum = 153;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 154;BA.debugLine="r.Target = lbl1";
_r.Target = (Object)(_lbl1.getObject());
 //BA.debugLineNum = 155;BA.debugLine="r.RunMethod2(\"setLines\", 1, \"java.lang.int\")";
_r.RunMethod2("setLines",BA.NumberToString(1),"java.lang.int");
 //BA.debugLineNum = 156;BA.debugLine="r.RunMethod2(\"setHorizontallyScrolling\", True, \"";
_r.RunMethod2("setHorizontallyScrolling",BA.ObjectToString(__c.True),"java.lang.boolean");
 //BA.debugLineNum = 157;BA.debugLine="r.RunMethod2(\"setEllipsize\", \"MIDDLE\", \"android.";
_r.RunMethod2("setEllipsize","MIDDLE","android.text.TextUtils$TruncateAt");
 };
 //BA.debugLineNum = 160;BA.debugLine="lstFichiers.AddCustomItem(ID, pnl, itemHeight)";
_lstfichiers._addcustomitem((Object)(_id),_pnl,_itemheight);
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
}
public String  _afficherimage(String _image) throws Exception{
int _marge = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
float _ratiobmp = 0f;
float _ratioimg = 0f;
float _diviseur = 0f;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 531;BA.debugLine="Private Sub AfficherImage(Image As String)";
 //BA.debugLineNum = 532;BA.debugLine="Dim Marge As Int: Marge = 2dip";
_marge = 0;
 //BA.debugLineNum = 532;BA.debugLine="Dim Marge As Int: Marge = 2dip";
_marge = __c.DipToCurrent((int) (2));
 //BA.debugLineNum = 533;BA.debugLine="pnlVisu.Initialize(\"\")";
_pnlvisu.Initialize(ba,"");
 //BA.debugLineNum = 534;BA.debugLine="pnlVisu.Color = Colors.Transparent";
_pnlvisu.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 535;BA.debugLine="ivVisu.Initialize(\"\")";
_ivvisu.Initialize(ba,"");
 //BA.debugLineNum = 536;BA.debugLine="pnlVisu.AddView(ivVisu, 0, 0, pnlFiles.Width - (2";
_pnlvisu.AddView((android.view.View)(_ivvisu.getObject()),(int) (0),(int) (0),(int) (_pnlfiles.getWidth()-(2*_marge)),(int) (_pnlfiles.getHeight()-(2*_marge)));
 //BA.debugLineNum = 537;BA.debugLine="lblVisu.Initialize(\"\")";
_lblvisu.Initialize(ba,"");
 //BA.debugLineNum = 538;BA.debugLine="lblVisu.Text = \"Please wait...\"";
_lblvisu.setText((Object)("Please wait..."));
 //BA.debugLineNum = 539;BA.debugLine="lblVisu.TextColor = FileTextColor1";
_lblvisu.setTextColor(_filetextcolor1);
 //BA.debugLineNum = 540;BA.debugLine="lblVisu.TextSize = 18";
_lblvisu.setTextSize((float) (18));
 //BA.debugLineNum = 541;BA.debugLine="lblVisu.Typeface = Typeface.DEFAULT_BOLD";
_lblvisu.setTypeface(__c.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 542;BA.debugLine="pnlVisu.AddView(lblVisu, 10dip, 10dip, pnlFiles.W";
_pnlvisu.AddView((android.view.View)(_lblvisu.getObject()),__c.DipToCurrent((int) (10)),__c.DipToCurrent((int) (10)),(int) (_pnlfiles.getWidth()-(2*_marge)-__c.DipToCurrent((int) (20))),__c.DipToCurrent((int) (30)));
 //BA.debugLineNum = 543;BA.debugLine="pnlFiles.AddView(pnlVisu, Marge, Marge, pnlFiles.";
_pnlfiles.AddView((android.view.View)(_pnlvisu.getObject()),_marge,_marge,(int) (_pnlfiles.getWidth()-(2*_marge)),(int) (_pnlfiles.getHeight()-(2*_marge)));
 //BA.debugLineNum = 544;BA.debugLine="svFichiers.Visible = False";
_svfichiers.setVisible(__c.False);
 //BA.debugLineNum = 545;BA.debugLine="DoEvents: DoEvents";
__c.DoEvents();
 //BA.debugLineNum = 545;BA.debugLine="DoEvents: DoEvents";
__c.DoEvents();
 //BA.debugLineNum = 546;BA.debugLine="Try";
try { //BA.debugLineNum = 547;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 548;BA.debugLine="bmp.InitializeSample(strChemin, Image, pnlVisu.W";
_bmp.InitializeSample(_strchemin,_image,_pnlvisu.getWidth(),_pnlvisu.getHeight());
 //BA.debugLineNum = 549;BA.debugLine="If bmp.Height <= pnlVisu.Height And bmp.Width <=";
if (_bmp.getHeight()<=_pnlvisu.getHeight() && _bmp.getWidth()<=_pnlvisu.getWidth()) { 
 //BA.debugLineNum = 551;BA.debugLine="ivVisu.Gravity = Gravity.CENTER";
_ivvisu.setGravity(__c.Gravity.CENTER);
 }else {
 //BA.debugLineNum = 553;BA.debugLine="Dim RatioBmp, RatioImg As Float";
_ratiobmp = 0f;
_ratioimg = 0f;
 //BA.debugLineNum = 554;BA.debugLine="RatioBmp = bmp.Width / bmp.Height";
_ratiobmp = (float) (_bmp.getWidth()/(double)_bmp.getHeight());
 //BA.debugLineNum = 555;BA.debugLine="RatioImg = pnlVisu.Width / pnlVisu.Height";
_ratioimg = (float) (_pnlvisu.getWidth()/(double)_pnlvisu.getHeight());
 //BA.debugLineNum = 556;BA.debugLine="If NumberFormat(RatioBmp, 1, 2) = NumberFormat(";
if ((__c.NumberFormat(_ratiobmp,(int) (1),(int) (2))).equals(__c.NumberFormat(_ratioimg,(int) (1),(int) (2)))) { 
 //BA.debugLineNum = 558;BA.debugLine="ivVisu.Gravity = Gravity.FILL";
_ivvisu.setGravity(__c.Gravity.FILL);
 }else {
 //BA.debugLineNum = 561;BA.debugLine="Dim Diviseur As Float";
_diviseur = 0f;
 //BA.debugLineNum = 562;BA.debugLine="If RatioImg > RatioBmp Then";
if (_ratioimg>_ratiobmp) { 
 //BA.debugLineNum = 563;BA.debugLine="Diviseur = bmp.Height / pnlVisu.Height";
_diviseur = (float) (_bmp.getHeight()/(double)_pnlvisu.getHeight());
 //BA.debugLineNum = 564;BA.debugLine="bmp = CreateScaledBitmap(bmp, Round(bmp.Width";
_bmp = _createscaledbitmap(_bmp,(int) (__c.Round(_bmp.getWidth()/(double)_diviseur/(double)__c.Density)),(int) (__c.Round(_pnlvisu.getHeight()/(double)__c.Density)));
 }else {
 //BA.debugLineNum = 567;BA.debugLine="Diviseur = bmp.Width / pnlVisu.Width";
_diviseur = (float) (_bmp.getWidth()/(double)_pnlvisu.getWidth());
 //BA.debugLineNum = 568;BA.debugLine="bmp = CreateScaledBitmap(bmp, Round(pnlVisu.W";
_bmp = _createscaledbitmap(_bmp,(int) (__c.Round(_pnlvisu.getWidth()/(double)__c.Density)),(int) (__c.Round(_bmp.getHeight()/(double)_diviseur/(double)__c.Density)));
 };
 //BA.debugLineNum = 571;BA.debugLine="ivVisu.Gravity = Gravity.NO_GRAVITY";
_ivvisu.setGravity(__c.Gravity.NO_GRAVITY);
 };
 };
 //BA.debugLineNum = 574;BA.debugLine="ivVisu.Bitmap = bmp";
_ivvisu.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 575;BA.debugLine="lblVisu.Text = \"\"";
_lblvisu.setText((Object)(""));
 //BA.debugLineNum = 576;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 577;BA.debugLine="r.Target = pnlVisu";
_r.Target = (Object)(_pnlvisu.getObject());
 //BA.debugLineNum = 578;BA.debugLine="r.SetOnClickListener(\"pnlVisu_Close\") 'We cannot";
_r.SetOnClickListener(ba,"pnlVisu_Close");
 } 
       catch (Exception e459) {
			ba.setLastException(e459); //BA.debugLineNum = 580;BA.debugLine="Msgbox(LastException.Message, \"Oooops\")";
__c.Msgbox(__c.LastException(ba).getMessage(),"Oooops",ba);
 //BA.debugLineNum = 581;BA.debugLine="pnlVisu_Close(Null)";
_pnlvisu_close(__c.Null);
 };
 //BA.debugLineNum = 583;BA.debugLine="End Sub";
return "";
}
public String  _affichertexte(String _texte) throws Exception{
int _marge = 0;
anywheresoftware.b4a.keywords.StringBuilderWrapper _contenu = null;
anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _reader = null;
String _ligne = "";
int _cpt = 0;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 593;BA.debugLine="Private Sub AfficherTexte(Texte As String)";
 //BA.debugLineNum = 594;BA.debugLine="Dim Marge As Int: Marge = 2dip";
_marge = 0;
 //BA.debugLineNum = 594;BA.debugLine="Dim Marge As Int: Marge = 2dip";
_marge = __c.DipToCurrent((int) (2));
 //BA.debugLineNum = 595;BA.debugLine="pnlVisu.Initialize(\"\")";
_pnlvisu.Initialize(ba,"");
 //BA.debugLineNum = 596;BA.debugLine="lblVisu.Initialize(\"\")";
_lblvisu.Initialize(ba,"");
 //BA.debugLineNum = 597;BA.debugLine="pnlVisu.AddView(lblVisu, 10dip, 10dip, pnlFiles.W";
_pnlvisu.AddView((android.view.View)(_lblvisu.getObject()),__c.DipToCurrent((int) (10)),__c.DipToCurrent((int) (10)),(int) (_pnlfiles.getWidth()-(2*_marge)-__c.DipToCurrent((int) (20))),(int) (_pnlfiles.getHeight()-(2*_marge)-__c.DipToCurrent((int) (20))));
 //BA.debugLineNum = 598;BA.debugLine="pnlFiles.AddView(pnlVisu, Marge, Marge, pnlFiles.";
_pnlfiles.AddView((android.view.View)(_pnlvisu.getObject()),_marge,_marge,(int) (_pnlfiles.getWidth()-(2*_marge)),(int) (_pnlfiles.getHeight()-(2*_marge)));
 //BA.debugLineNum = 599;BA.debugLine="pnlVisu.Color = Colors.Transparent";
_pnlvisu.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 600;BA.debugLine="svFichiers.Visible = False";
_svfichiers.setVisible(__c.False);
 //BA.debugLineNum = 601;BA.debugLine="lblVisu.TextColor = FileTextColor1";
_lblvisu.setTextColor(_filetextcolor1);
 //BA.debugLineNum = 602;BA.debugLine="lblVisu.TextSize = 16";
_lblvisu.setTextSize((float) (16));
 //BA.debugLineNum = 603;BA.debugLine="lblVisu.Typeface = Typeface.DEFAULT";
_lblvisu.setTypeface(__c.Typeface.DEFAULT);
 //BA.debugLineNum = 604;BA.debugLine="Try";
try { //BA.debugLineNum = 605;BA.debugLine="Dim Contenu As StringBuilder: Contenu.Initialize";
_contenu = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 605;BA.debugLine="Dim Contenu As StringBuilder: Contenu.Initialize";
_contenu.Initialize();
 //BA.debugLineNum = 606;BA.debugLine="Dim Reader As TextReader, Ligne As String, Cpt A";
_reader = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
_ligne = "";
_cpt = 0;
 //BA.debugLineNum = 607;BA.debugLine="Reader.Initialize(File.OpenInput(strChemin, Text";
_reader.Initialize((java.io.InputStream)(__c.File.OpenInput(_strchemin,_texte).getObject()));
 //BA.debugLineNum = 608;BA.debugLine="Ligne = Reader.ReadLine";
_ligne = _reader.ReadLine();
 //BA.debugLineNum = 609;BA.debugLine="Do While Ligne <> Null";
while (_ligne!= null) {
 //BA.debugLineNum = 610;BA.debugLine="Cpt = Cpt + 1";
_cpt = (int) (_cpt+1);
 //BA.debugLineNum = 611;BA.debugLine="If Cpt > 50 Then";
if (_cpt>50) { 
 //BA.debugLineNum = 612;BA.debugLine="Contenu.Append(\"--- Lines after 50 are skipped";
_contenu.Append("--- Lines after 50 are skipped ---");
 //BA.debugLineNum = 613;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 615;BA.debugLine="Contenu.Append(Ligne).Append(CRLF)";
_contenu.Append(_ligne).Append(__c.CRLF);
 //BA.debugLineNum = 616;BA.debugLine="Ligne = Reader.ReadLine";
_ligne = _reader.ReadLine();
 }
;
 //BA.debugLineNum = 618;BA.debugLine="Reader.Close";
_reader.Close();
 //BA.debugLineNum = 619;BA.debugLine="lblVisu.Text = Contenu";
_lblvisu.setText((Object)(_contenu.getObject()));
 //BA.debugLineNum = 620;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 621;BA.debugLine="r.Target = pnlVisu";
_r.Target = (Object)(_pnlvisu.getObject());
 //BA.debugLineNum = 622;BA.debugLine="r.SetOnClickListener(\"pnlVisu_Close\") 'We cannot";
_r.SetOnClickListener(ba,"pnlVisu_Close");
 } 
       catch (Exception e501) {
			ba.setLastException(e501); //BA.debugLineNum = 624;BA.debugLine="Msgbox(LastException.Message, \"Oooops\")";
__c.Msgbox(__c.LastException(ba).getMessage(),"Oooops",ba);
 //BA.debugLineNum = 625;BA.debugLine="Reader.Close";
_reader.Close();
 //BA.debugLineNum = 626;BA.debugLine="pnlVisu_Close(Null)";
_pnlvisu_close(__c.Null);
 };
 //BA.debugLineNum = 628;BA.debugLine="End Sub";
return "";
}
public String  _anim_animationend() throws Exception{
 //BA.debugLineNum = 1103;BA.debugLine="Private Sub Anim_AnimationEnd";
 //BA.debugLineNum = 1104;BA.debugLine="pnlRange.Visible = False";
_pnlrange.setVisible(__c.False);
 //BA.debugLineNum = 1105;BA.debugLine="End Sub";
return "";
}
public String  _btndelete_longclick() throws Exception{
String _aux = "";
 //BA.debugLineNum = 1018;BA.debugLine="Private Sub btnDelete_LongClick";
 //BA.debugLineNum = 1021;BA.debugLine="Dim aux As String";
_aux = "";
 //BA.debugLineNum = 1022;BA.debugLine="If edtFilename.Text=\"\" Then";
if ((_edtfilename.getText()).equals("")) { 
 //BA.debugLineNum = 1023;BA.debugLine="ToastMessageShow(\"Elige un fichero\",True)";
__c.ToastMessageShow("Elige un fichero",__c.True);
 }else {
 //BA.debugLineNum = 1025;BA.debugLine="Selection.ChosenFile = edtFilename.Text";
_selection.ChosenFile = _edtfilename.getText();
 //BA.debugLineNum = 1027;BA.debugLine="If  F1A.strModelo=edtFilename.Text Or F1B.strMod";
if ((_f1a._strmodelo).equals(_edtfilename.getText()) || (_f1b._strmodelo).equals(_edtfilename.getText()) || (_f1c._strmodelo).equals(_edtfilename.getText())) { 
 //BA.debugLineNum = 1028;BA.debugLine="ToastMessageShow(\"Fichero abierto, Por favor cie";
__c.ToastMessageShow("Fichero abierto, Por favor cierrelo",__c.True);
 }else {
 //BA.debugLineNum = 1031;BA.debugLine="aux=strChemin.SubString(11)";
_aux = _strchemin.substring((int) (11));
 //BA.debugLineNum = 1032;BA.debugLine="Selection.ChosenPath = strChemin";
_selection.ChosenPath = _strchemin;
 //BA.debugLineNum = 1033;BA.debugLine="Selection.ChosenFile = edtFilename.Text";
_selection.ChosenFile = _edtfilename.getText();
 //BA.debugLineNum = 1034;BA.debugLine="edtFilename.Text=\"\"";
_edtfilename.setText((Object)(""));
 //BA.debugLineNum = 1035;BA.debugLine="File.Delete(File.DirRootExternal&aux,Selection.C";
__c.File.Delete(__c.File.getDirRootExternal()+_aux,_selection.ChosenFile);
 //BA.debugLineNum = 1036;BA.debugLine="If strChemin.EndsWith(\"/\") And strChemin <> \"/\" T";
if (_strchemin.endsWith("/") && (_strchemin).equals("/") == false) { 
_strchemin = _strchemin.substring((int) (0),_strchemin.length());};
 //BA.debugLineNum = 1037;BA.debugLine="ReadFolder(strChemin)";
_readfolder(_strchemin);
 //BA.debugLineNum = 1038;BA.debugLine="CommonExplorer";
_commonexplorer();
 };
 };
 //BA.debugLineNum = 1046;BA.debugLine="End Sub";
return "";
}
public String  _btnok_click() throws Exception{
String _aux = "";
 //BA.debugLineNum = 851;BA.debugLine="Private Sub btnOK_Click";
 //BA.debugLineNum = 852;BA.debugLine="Dim aux As String";
_aux = "";
 //BA.debugLineNum = 854;BA.debugLine="If edtFilename.Text =\"\" Then";
if ((_edtfilename.getText()).equals("")) { 
 }else {
 //BA.debugLineNum = 860;BA.debugLine="Selection.Canceled = False";
_selection.Canceled = __c.False;
 //BA.debugLineNum = 861;BA.debugLine="If bOnlyFolders Then";
if (_bonlyfolders) { 
 //BA.debugLineNum = 862;BA.debugLine="If edtFilename.Text = \"\" Then";
if ((_edtfilename.getText()).equals("")) { 
 //BA.debugLineNum = 863;BA.debugLine="Selection.ChosenPath = strChemin";
_selection.ChosenPath = _strchemin;
 }else {
 //BA.debugLineNum = 865;BA.debugLine="Selection.ChosenPath = nombre";
_selection.ChosenPath = _nombre;
 };
 //BA.debugLineNum = 867;BA.debugLine="Selection.ChosenFile = \"\"";
_selection.ChosenFile = "";
 }else {
 //BA.debugLineNum = 869;BA.debugLine="Selection.ChosenPath = strChemin";
_selection.ChosenPath = _strchemin;
 //BA.debugLineNum = 870;BA.debugLine="Selection.ChosenFile = nombre";
_selection.ChosenFile = _nombre;
 };
 //BA.debugLineNum = 872;BA.debugLine="WaitUntilOK = False";
_waituntilok = __c.False;
 //BA.debugLineNum = 876;BA.debugLine="ToastMessageShow(edtFilename.Text,True)";
__c.ToastMessageShow(_edtfilename.getText(),__c.True);
 //BA.debugLineNum = 880;BA.debugLine="ToastMessageShow(strChemin.SubString(20),True)";
__c.ToastMessageShow(_strchemin.substring((int) (20)),__c.True);
 //BA.debugLineNum = 971;BA.debugLine="If	 File.Exists(File.DirRootExternal&\"Consola/F1";
if (__c.File.Exists(__c.File.getDirRootExternal()+"Consola/F1A",_edtfilename.getText()) || __c.File.Exists(__c.File.getDirRootExternal()+"Consola/F1B",_edtfilename.getText()) || __c.File.Exists(__c.File.getDirRootExternal()+"Consola/F1C",_edtfilename.getText())) { 
 }else {
 //BA.debugLineNum = 977;BA.debugLine="If strChemin.SubString(20)=\"F1A\" Then";
if ((_strchemin.substring((int) (20))).equals("F1A")) { 
 //BA.debugLineNum = 978;BA.debugLine="F1A.strfichero=edtFilename.Text";
_f1a._strfichero = _edtfilename.getText();
 //BA.debugLineNum = 979;BA.debugLine="F1A.strspinner=\"PULSE AQUI\"";
_f1a._strspinner = "PULSE AQUI";
 //BA.debugLineNum = 981;BA.debugLine="CallSub(F1A,\"ficheronuevo\")";
__c.CallSubNew(ba,(Object)(_f1a.getObject()),"ficheronuevo");
 };
 //BA.debugLineNum = 983;BA.debugLine="If strChemin.SubString(20)=\"F1B\" Then";
if ((_strchemin.substring((int) (20))).equals("F1B")) { 
 //BA.debugLineNum = 984;BA.debugLine="F1B.strfichero=edtFilename.text";
_f1b._strfichero = _edtfilename.getText();
 //BA.debugLineNum = 985;BA.debugLine="F1B.strspinner=\"PULSE AQUI\"";
_f1b._strspinner = "PULSE AQUI";
 //BA.debugLineNum = 987;BA.debugLine="CallSub(F1B,\"ficheronuevo\")";
__c.CallSubNew(ba,(Object)(_f1b.getObject()),"ficheronuevo");
 };
 //BA.debugLineNum = 989;BA.debugLine="If strChemin.SubString(20)=\"F1C\" Then";
if ((_strchemin.substring((int) (20))).equals("F1C")) { 
 //BA.debugLineNum = 990;BA.debugLine="F1C.strfichero=edtFilename.Text";
_f1c._strfichero = _edtfilename.getText();
 //BA.debugLineNum = 991;BA.debugLine="F1C.strspinner=\"PULSE AQUI\"";
_f1c._strspinner = "PULSE AQUI";
 //BA.debugLineNum = 992;BA.debugLine="CallSub(F1C,\"ficheronuevo\")";
__c.CallSubNew(ba,(Object)(_f1c.getObject()),"ficheronuevo");
 };
 };
 };
 //BA.debugLineNum = 1002;BA.debugLine="End Sub";
return "";
}
public int  _calcnewtop() throws Exception{
 //BA.debugLineNum = 1094;BA.debugLine="Private Sub CalcNewTop As Int";
 //BA.debugLineNum = 1095;BA.debugLine="Return (svFichiers.VerticalScrollPosition / (svFi";
if (true) return (int) ((_svfichiers.getVerticalScrollPosition()/(double)(_svfichiers.getPanel().getHeight()-_svfichiers.getHeight())*_maxpos));
 //BA.debugLineNum = 1096;BA.debugLine="End Sub";
return 0;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Type typResult(Canceled As Boolean, ChosenPath As";
;
 //BA.debugLineNum = 5;BA.debugLine="Public BorderColor, BackgroundColor As Int";
_bordercolor = 0;
_backgroundcolor = 0;
 //BA.debugLineNum = 6;BA.debugLine="Public FolderTextColor As Int";
_foldertextcolor = 0;
 //BA.debugLineNum = 7;BA.debugLine="Public FileTextColor1, FileTextColor2 As Int";
_filetextcolor1 = 0;
_filetextcolor2 = 0;
 //BA.debugLineNum = 8;BA.debugLine="Public DividerColor As Int";
_dividercolor = 0;
 //BA.debugLineNum = 9;BA.debugLine="Public DialogRect As Rect";
_dialogrect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Public FastScrollEnabled As Boolean";
_fastscrollenabled = false;
 //BA.debugLineNum = 11;BA.debugLine="Public Selection As typResult";
_selection = new com.delozoya.Consola.Completa.clsexplorer._typresult();
 //BA.debugLineNum = 12;BA.debugLine="Public Ellipsis As Boolean";
_ellipsis = false;
 //BA.debugLineNum = 14;BA.debugLine="Private actEcran As Activity";
_actecran = new anywheresoftware.b4a.objects.ActivityWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private strChemin As String";
_strchemin = "";
 //BA.debugLineNum = 16;BA.debugLine="Private lstFiltre As List";
_lstfiltre = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 17;BA.debugLine="Private bOnlyFolders As Boolean";
_bonlyfolders = false;
 //BA.debugLineNum = 18;BA.debugLine="Private bVisualiser As Boolean";
_bvisualiser = false;
 //BA.debugLineNum = 19;BA.debugLine="Private bMultiFolderSelection As Boolean";
_bmultifolderselection = false;
 //BA.debugLineNum = 20;BA.debugLine="Private bMultiFileSelection As Boolean";
_bmultifileselection = false;
 //BA.debugLineNum = 21;BA.debugLine="Private strBtnOKTxt As String";
_strbtnoktxt = "";
 //BA.debugLineNum = 23;BA.debugLine="Private pnlMasque As Panel";
_pnlmasque = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private pnlCadre As Panel";
_pnlcadre = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private pnlFiles As Panel";
_pnlfiles = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private svFichiers As ScrollView2D";
_svfichiers = new flm.b4a.scrollview2d.ScrollView2DWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lstFichiers As ClsCheckList";
_lstfichiers = new com.delozoya.Consola.Completa.clschecklist();
 //BA.debugLineNum = 28;BA.debugLine="Private itemHeight As Int: itemHeight = 55dip";
_itemheight = 0;
 //BA.debugLineNum = 28;BA.debugLine="Private itemHeight As Int: itemHeight = 55dip";
_itemheight = __c.DipToCurrent((int) (55));
 //BA.debugLineNum = 29;BA.debugLine="Private pnlVisu As Panel";
_pnlvisu = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private lblVisu As Label";
_lblvisu = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private ivVisu As ImageView";
_ivvisu = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private pnlCartouche As Panel";
_pnlcartouche = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private edtFilename As EditText";
_edtfilename = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private btnOK As Button";
_btnok = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private WaitUntilOK As Boolean";
_waituntilok = false;
 //BA.debugLineNum = 36;BA.debugLine="Private btndelete As Button";
_btndelete = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private pnlRange As Panel";
_pnlrange = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private pnlDisplay As Panel";
_pnldisplay = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private Anim As Animation";
_anim = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private TimeOut As Timer";
_timeout = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 42;BA.debugLine="Private Duration As Int";
_duration = 0;
 //BA.debugLineNum = 43;BA.debugLine="Private MaxPos As Int";
_maxpos = 0;
 //BA.debugLineNum = 44;BA.debugLine="Private bIgnoreEvent As Boolean";
_bignoreevent = false;
 //BA.debugLineNum = 45;BA.debugLine="Private bUserMovingPnl As Boolean";
_busermovingpnl = false;
 //BA.debugLineNum = 46;BA.debugLine="Private bWaitForScroll As Boolean";
_bwaitforscroll = false;
 //BA.debugLineNum = 48;BA.debugLine="Dim ime1 As IME";
_ime1 = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 49;BA.debugLine="Dim nombre As String";
_nombre = "";
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public String  _commonexplorer() throws Exception{
 //BA.debugLineNum = 448;BA.debugLine="Private Sub CommonExplorer";
 //BA.debugLineNum = 449;BA.debugLine="Try";
try { //BA.debugLineNum = 450;BA.debugLine="If FastScrollEnabled Then InitializeScrollPanel";
if (_fastscrollenabled) { 
_initializescrollpanel();};
 //BA.debugLineNum = 452;BA.debugLine="Selection.Canceled = True";
_selection.Canceled = __c.True;
 //BA.debugLineNum = 453;BA.debugLine="Selection.ChosenPath = \"\"";
_selection.ChosenPath = "";
 //BA.debugLineNum = 454;BA.debugLine="Selection.ChosenFile = \"\"";
_selection.ChosenFile = "";
 //BA.debugLineNum = 455;BA.debugLine="edtFilename.RequestFocus";
_edtfilename.RequestFocus();
 //BA.debugLineNum = 457;BA.debugLine="Do While WaitUntilOK";
while (_waituntilok) {
 //BA.debugLineNum = 459;BA.debugLine="DoEvents";
__c.DoEvents();
 }
;
 //BA.debugLineNum = 462;BA.debugLine="pnlMasque.RemoveView";
_pnlmasque.RemoveView();
 //BA.debugLineNum = 463;BA.debugLine="pnlMasque = Null";
_pnlmasque.setObject((android.view.ViewGroup)(__c.Null));
 } 
       catch (Exception e365) {
			ba.setLastException(e365); };
 //BA.debugLineNum = 467;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _createscaledbitmap(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _original,int _width,int _height) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b = null;
 //BA.debugLineNum = 521;BA.debugLine="Private Sub CreateScaledBitmap(Original As Bitmap,";
 //BA.debugLineNum = 522;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 523;BA.debugLine="Dim b As Bitmap";
_b = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 524;BA.debugLine="b = r.RunStaticMethod(\"android.graphics.Bitmap\",";
_b.setObject((android.graphics.Bitmap)(_r.RunStaticMethod("android.graphics.Bitmap","createScaledBitmap",new Object[]{(Object)(_original.getObject()),(Object)(_width),(Object)(_height),(Object)(__c.True)},new String[]{"android.graphics.Bitmap","java.lang.int","java.lang.int","java.lang.boolean"})));
 //BA.debugLineNum = 527;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 528;BA.debugLine="End Sub";
return null;
}
public String  _displaysize(double _sizeoct) throws Exception{
String[] _txtunits = null;
int _unité = 0;
 //BA.debugLineNum = 91;BA.debugLine="Private Sub DisplaySize(SizeOct As Double) As Stri";
 //BA.debugLineNum = 92;BA.debugLine="Dim txtUnits(4) As String";
_txtunits = new String[(int) (4)];
java.util.Arrays.fill(_txtunits,"");
 //BA.debugLineNum = 93;BA.debugLine="txtUnits = Array As String(\"bytes\", \"Kb\", \"Mb\", \"";
_txtunits = new String[]{"bytes","Kb","Mb","Gb"};
 //BA.debugLineNum = 94;BA.debugLine="Dim Unité As Int";
_unité = 0;
 //BA.debugLineNum = 95;BA.debugLine="Unité = 0";
_unité = (int) (0);
 //BA.debugLineNum = 96;BA.debugLine="Do While SizeOct > 1024";
while (_sizeoct>1024) {
 //BA.debugLineNum = 97;BA.debugLine="Unité = Unité + 1";
_unité = (int) (_unité+1);
 //BA.debugLineNum = 98;BA.debugLine="SizeOct = SizeOct / 1024";
_sizeoct = _sizeoct/(double)1024;
 }
;
 //BA.debugLineNum = 100;BA.debugLine="If SizeOct <> Floor(SizeOct) Then";
if (_sizeoct!=__c.Floor(_sizeoct)) { 
 //BA.debugLineNum = 101;BA.debugLine="Return NumberFormat(SizeOct, 1, 1) & \" \" & txtUn";
if (true) return __c.NumberFormat(_sizeoct,(int) (1),(int) (1))+" "+_txtunits[_unité];
 }else {
 //BA.debugLineNum = 103;BA.debugLine="Return SizeOct & \" \" & txtUnits(Unité)";
if (true) return BA.NumberToString(_sizeoct)+" "+_txtunits[_unité];
 };
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public String  _dlg_hasfocus(Object _viewtag,boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 475;BA.debugLine="Private Sub dlg_HasFocus(ViewTag As Object, HasFoc";
 //BA.debugLineNum = 476;BA.debugLine="If Not(HasFocus) Then edtFilename.RequestFocus";
if (__c.Not(_hasfocus)) { 
_edtfilename.RequestFocus();};
 //BA.debugLineNum = 477;BA.debugLine="End Sub";
return "";
}
public boolean  _dlg_keypress(Object _viewtag,int _keycode,Object _keyevent) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
int _keyaction = 0;
 //BA.debugLineNum = 480;BA.debugLine="Private Sub dlg_KeyPress(ViewTag As Object, KeyCod";
 //BA.debugLineNum = 481;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 482;BA.debugLine="r.Target = KeyEvent";
_r.Target = _keyevent;
 //BA.debugLineNum = 483;BA.debugLine="Dim KeyAction As Int = r.RunMethod(\"getAction\")";
_keyaction = (int)(BA.ObjectToNumber(_r.RunMethod("getAction")));
 //BA.debugLineNum = 484;BA.debugLine="Select Case KeyCode";
switch (BA.switchObjectToInt(_keycode,__c.KeyCodes.KEYCODE_BACK,__c.KeyCodes.KEYCODE_MENU,__c.KeyCodes.KEYCODE_SEARCH)) {
case 0: {
 //BA.debugLineNum = 486;BA.debugLine="If KeyAction = 1 Then 'ACTION_UP";
if (_keyaction==1) { 
 //BA.debugLineNum = 487;BA.debugLine="If pnlVisu.IsInitialized Then";
if (_pnlvisu.IsInitialized()) { 
 //BA.debugLineNum = 488;BA.debugLine="pnlVisu_Close(Null)";
_pnlvisu_close(__c.Null);
 }else {
 //BA.debugLineNum = 490;BA.debugLine="WaitUntilOK = False";
_waituntilok = __c.False;
 };
 };
 //BA.debugLineNum = 493;BA.debugLine="Return True";
if (true) return __c.True;
 break; }
case 1: {
 //BA.debugLineNum = 495;BA.debugLine="Return True";
if (true) return __c.True;
 break; }
case 2: {
 //BA.debugLineNum = 497;BA.debugLine="Return True";
if (true) return __c.True;
 break; }
}
;
 //BA.debugLineNum = 499;BA.debugLine="Return False";
if (true) return __c.False;
 //BA.debugLineNum = 500;BA.debugLine="End Sub";
return false;
}
public String  _edtfilename_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 1004;BA.debugLine="Sub edtFilename_TextChanged (Old As String, New As";
 //BA.debugLineNum = 1006;BA.debugLine="Try";
try { //BA.debugLineNum = 1007;BA.debugLine="If New.Length<=19 Then";
if (_new.length()<=19) { 
 //BA.debugLineNum = 1008;BA.debugLine="edtFilename.Text=New";
_edtfilename.setText((Object)(_new));
 }else {
 //BA.debugLineNum = 1010;BA.debugLine="edtFilename.Text=Old";
_edtfilename.setText((Object)(_old));
 };
 } 
       catch (Exception e725) {
			ba.setLastException(e725); };
 //BA.debugLineNum = 1016;BA.debugLine="End Sub";
return "";
}
public String  _explorer() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
int _marginbord = 0;
int _margin = 0;
int _margincartouche = 0;
int _hauteurcartouche = 0;
int _largeurbtn = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd_pnlcadre = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd_pnl = null;
int _largeur = 0;
int _hauteur = 0;
anywheresoftware.b4a.objects.drawable.GradientDrawable _gd_pnlcartouche = null;
int[] _clrs = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bmp0 = null;
 //BA.debugLineNum = 238;BA.debugLine="Public Sub Explorer";
 //BA.debugLineNum = 242;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 243;BA.debugLine="Dim MarginBord As Int: MarginBord = 3dip";
_marginbord = 0;
 //BA.debugLineNum = 243;BA.debugLine="Dim MarginBord As Int: MarginBord = 3dip";
_marginbord = __c.DipToCurrent((int) (3));
 //BA.debugLineNum = 244;BA.debugLine="Dim Margin As Int: Margin = 8dip";
_margin = 0;
 //BA.debugLineNum = 244;BA.debugLine="Dim Margin As Int: Margin = 8dip";
_margin = __c.DipToCurrent((int) (8));
 //BA.debugLineNum = 245;BA.debugLine="Dim MarginCartouche As Int: MarginCartouche = 4di";
_margincartouche = 0;
 //BA.debugLineNum = 245;BA.debugLine="Dim MarginCartouche As Int: MarginCartouche = 4di";
_margincartouche = __c.DipToCurrent((int) (4));
 //BA.debugLineNum = 246;BA.debugLine="Dim HauteurCartouche As Int: HauteurCartouche = 5";
_hauteurcartouche = 0;
 //BA.debugLineNum = 246;BA.debugLine="Dim HauteurCartouche As Int: HauteurCartouche = 5";
_hauteurcartouche = __c.DipToCurrent((int) (50));
 //BA.debugLineNum = 247;BA.debugLine="Dim LargeurBtn As Int: LargeurBtn = 70dip";
_largeurbtn = 0;
 //BA.debugLineNum = 247;BA.debugLine="Dim LargeurBtn As Int: LargeurBtn = 70dip";
_largeurbtn = __c.DipToCurrent((int) (70));
 //BA.debugLineNum = 248;BA.debugLine="Dim cd_pnlCadre, cd_pnl As ColorDrawable";
_cd_pnlcadre = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_cd_pnl = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 250;BA.debugLine="pnlMasque.Initialize(\"\")";
_pnlmasque.Initialize(ba,"");
 //BA.debugLineNum = 251;BA.debugLine="pnlMasque.Color = Colors.Transparent";
_pnlmasque.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 252;BA.debugLine="r.Target = pnlMasque";
_r.Target = (Object)(_pnlmasque.getObject());
 //BA.debugLineNum = 253;BA.debugLine="r.SetOnTouchListener(\"pnl_BlockTouch\")";
_r.SetOnTouchListener(ba,"pnl_BlockTouch");
 //BA.debugLineNum = 255;BA.debugLine="pnlCadre.Initialize(\"\")";
_pnlcadre.Initialize(ba,"");
 //BA.debugLineNum = 256;BA.debugLine="cd_pnlCadre.Initialize(BorderColor, 5)";
_cd_pnlcadre.Initialize(_bordercolor,(int) (5));
 //BA.debugLineNum = 257;BA.debugLine="pnlCadre.Background = cd_pnlCadre";
_pnlcadre.setBackground((android.graphics.drawable.Drawable)(_cd_pnlcadre.getObject()));
 //BA.debugLineNum = 259;BA.debugLine="pnlFiles.Initialize(\"\")";
_pnlfiles.Initialize(ba,"");
 //BA.debugLineNum = 260;BA.debugLine="cd_pnl.Initialize(Colors.White, 5)";
_cd_pnl.Initialize(__c.Colors.White,(int) (5));
 //BA.debugLineNum = 261;BA.debugLine="pnlFiles.Background = cd_pnl";
_pnlfiles.setBackground((android.graphics.drawable.Drawable)(_cd_pnl.getObject()));
 //BA.debugLineNum = 262;BA.debugLine="svFichiers.Initialize(-1, 0, \"SVF\")";
_svfichiers.Initialize(ba,(int) (-1),(int) (0),"SVF");
 //BA.debugLineNum = 263;BA.debugLine="svFichiers.Color = Colors.White";
_svfichiers.setColor(__c.Colors.White);
 //BA.debugLineNum = 264;BA.debugLine="Dim Largeur, Hauteur As Int";
_largeur = 0;
_hauteur = 0;
 //BA.debugLineNum = 265;BA.debugLine="Largeur = DialogRect.Right - DialogRect.Left";
_largeur = (int) (_dialogrect.getRight()-_dialogrect.getLeft());
 //BA.debugLineNum = 266;BA.debugLine="Hauteur = DialogRect.Bottom - DialogRect.Top";
_hauteur = (int) (_dialogrect.getBottom()-_dialogrect.getTop());
 //BA.debugLineNum = 267;BA.debugLine="pnlFiles.AddView(svFichiers, Margin, Margin, Larg";
_pnlfiles.AddView((android.view.View)(_svfichiers.getObject()),_margin,_margin,(int) (_largeur-(2*_marginbord)-(2*_margin)),(int) (_hauteur-(2*_marginbord)-(2*_margin)-_hauteurcartouche));
 //BA.debugLineNum = 268;BA.debugLine="r.Target = svFichiers";
_r.Target = (Object)(_svfichiers.getObject());
 //BA.debugLineNum = 269;BA.debugLine="r.SetOnKeyListener(\"dlg_KeyPress\")";
_r.SetOnKeyListener(ba,"dlg_KeyPress");
 //BA.debugLineNum = 270;BA.debugLine="r.SetOnFocusListener(\"dlg_HasFocus\")";
_r.SetOnFocusListener(ba,"dlg_HasFocus");
 //BA.debugLineNum = 272;BA.debugLine="pnlCartouche.Initialize(\"\")";
_pnlcartouche.Initialize(ba,"");
 //BA.debugLineNum = 273;BA.debugLine="Dim gd_pnlCartouche As GradientDrawable, Clrs(2)";
_gd_pnlcartouche = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
_clrs = new int[(int) (2)];
;
 //BA.debugLineNum = 274;BA.debugLine="Clrs(0) = Colors.white";
_clrs[(int) (0)] = __c.Colors.White;
 //BA.debugLineNum = 275;BA.debugLine="Clrs(1) =  Colors.white";
_clrs[(int) (1)] = __c.Colors.White;
 //BA.debugLineNum = 276;BA.debugLine="gd_pnlCartouche.Initialize(\"TOP_BOTTOM\", Clrs)";
_gd_pnlcartouche.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"TOP_BOTTOM"),_clrs);
 //BA.debugLineNum = 277;BA.debugLine="gd_pnlCartouche.CornerRadius = 5";
_gd_pnlcartouche.setCornerRadius((float) (5));
 //BA.debugLineNum = 278;BA.debugLine="pnlCartouche.Background = gd_pnlCartouche";
_pnlcartouche.setBackground((android.graphics.drawable.Drawable)(_gd_pnlcartouche.getObject()));
 //BA.debugLineNum = 279;BA.debugLine="edtFilename.Initialize(\"\")";
_edtfilename.Initialize(ba,"");
 //BA.debugLineNum = 280;BA.debugLine="edtFilename.Enabled=True";
_edtfilename.setEnabled(__c.True);
 //BA.debugLineNum = 281;BA.debugLine="edtFilename.Color=Colors.RGB(192,192,192)";
_edtfilename.setColor(__c.Colors.RGB((int) (192),(int) (192),(int) (192)));
 //BA.debugLineNum = 282;BA.debugLine="edtFilename.TextSize = 30";
_edtfilename.setTextSize((float) (30));
 //BA.debugLineNum = 283;BA.debugLine="edtFilename.Typeface=Typeface.DEFAULT_BOLD";
_edtfilename.setTypeface(__c.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 284;BA.debugLine="edtFilename.TextColor=Colors.Black";
_edtfilename.setTextColor(__c.Colors.Black);
 //BA.debugLineNum = 286;BA.debugLine="edtFilename.InputType = Bit.Or(edtFilename.InputT";
_edtfilename.setInputType(__c.Bit.Or(_edtfilename.getInputType(),(int) (0x80000)));
 //BA.debugLineNum = 287;BA.debugLine="edtFilename.SingleLine = True";
_edtfilename.setSingleLine(__c.True);
 //BA.debugLineNum = 288;BA.debugLine="edtFilename.Wrap = False";
_edtfilename.setWrap(__c.False);
 //BA.debugLineNum = 289;BA.debugLine="r.Target = edtFilename";
_r.Target = (Object)(_edtfilename.getObject());
 //BA.debugLineNum = 290;BA.debugLine="r.SetOnKeyListener(\"dlg_KeyPress\")";
_r.SetOnKeyListener(ba,"dlg_KeyPress");
 //BA.debugLineNum = 291;BA.debugLine="r.SetOnFocusListener(\"dlg_HasFocus\")";
_r.SetOnFocusListener(ba,"dlg_HasFocus");
 //BA.debugLineNum = 292;BA.debugLine="ime1.SetCustomFilter(edtFilename,edtFilename.INPU";
_ime1.SetCustomFilter((android.widget.EditText)(_edtfilename.getObject()),_edtfilename.INPUT_TYPE_TEXT,"qwertyuiopasdfghjklñzxcvbnm-_0123456789QWERTYUIOPASDFGHJKLÑZXCVBNM");
 //BA.debugLineNum = 293;BA.debugLine="ime1.SetLengthFilter(edtFilename,20)";
_ime1.SetLengthFilter((android.widget.EditText)(_edtfilename.getObject()),(int) (20));
 //BA.debugLineNum = 296;BA.debugLine="pnlCartouche.AddView(edtFilename, MarginCartouche";
_pnlcartouche.AddView((android.view.View)(_edtfilename.getObject()),(int) (_margincartouche+__c.DipToCurrent((int) (1))),(int) (_margincartouche+__c.DipToCurrent((int) (1))),(int) (_largeur-(2*_marginbord)-(2*_margincartouche)-(3*_largeurbtn)),(int) (_hauteurcartouche-_margincartouche+(20*_marginbord)));
 //BA.debugLineNum = 298;BA.debugLine="btnOK.Initialize(\"btnOK\")";
_btnok.Initialize(ba,"btnOK");
 //BA.debugLineNum = 299;BA.debugLine="btnOK.Text = strBtnOKTxt";
_btnok.setText((Object)(_strbtnoktxt));
 //BA.debugLineNum = 300;BA.debugLine="pnlCartouche.AddView(btnOK, edtFilename.Width + (";
_pnlcartouche.AddView((android.view.View)(_btnok.getObject()),(int) (_edtfilename.getWidth()+(8*_margincartouche)),(int) (_margincartouche+__c.DipToCurrent((int) (1))),(int) (_largeurbtn*2.5),(int) (_hauteurcartouche-_margincartouche+(20*_marginbord)));
 //BA.debugLineNum = 305;BA.debugLine="pnlCadre.AddView(pnlFiles, MarginBord, MarginBord";
_pnlcadre.AddView((android.view.View)(_pnlfiles.getObject()),_marginbord,_marginbord,(int) (_largeur-(2*_marginbord)),(int) (_hauteur-_hauteurcartouche-(20*_marginbord)));
 //BA.debugLineNum = 306;BA.debugLine="pnlCadre.AddView(pnlCartouche, MarginBord, Hauteu";
_pnlcadre.AddView((android.view.View)(_pnlcartouche.getObject()),_marginbord,(int) (_hauteur-_hauteurcartouche-(20*_marginbord)),(int) (_largeur-(2*_marginbord)),(int) (_hauteurcartouche+(20*_marginbord)));
 //BA.debugLineNum = 307;BA.debugLine="pnlMasque.AddView(pnlCadre, DialogRect.Left, Dial";
_pnlmasque.AddView((android.view.View)(_pnlcadre.getObject()),_dialogrect.getLeft(),_dialogrect.getTop(),_largeur,_hauteur);
 //BA.debugLineNum = 308;BA.debugLine="actEcran.AddView(pnlMasque, 0, 0, 100%x, 100%y)";
_actecran.AddView((android.view.View)(_pnlmasque.getObject()),(int) (0),(int) (0),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 //BA.debugLineNum = 309;BA.debugLine="btnOK.TextColor=Colors.Black";
_btnok.setTextColor(__c.Colors.Black);
 //BA.debugLineNum = 310;BA.debugLine="btnOK.Typeface=Typeface.DEFAULT_BOLD";
_btnok.setTypeface(__c.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 311;BA.debugLine="btnOK.TextSize=40";
_btnok.setTextSize((float) (40));
 //BA.debugLineNum = 314;BA.debugLine="Dim bmp0 As BitmapDrawable";
_bmp0 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 316;BA.debugLine="bmp0.Initialize(LoadBitmap(File.DirAssets, \"2.png\"";
_bmp0.Initialize((android.graphics.Bitmap)(__c.LoadBitmap(__c.File.getDirAssets(),"2.png").getObject()));
 //BA.debugLineNum = 320;BA.debugLine="If strChemin.EndsWith(\"/\") And strChemin <> \"/\" T";
if (_strchemin.endsWith("/") && (_strchemin).equals("/") == false) { 
_strchemin = _strchemin.substring((int) (0),_strchemin.length());};
 //BA.debugLineNum = 321;BA.debugLine="ReadFolder(strChemin)";
_readfolder(_strchemin);
 //BA.debugLineNum = 322;BA.debugLine="CommonExplorer";
_commonexplorer();
 //BA.debugLineNum = 323;BA.debugLine="End Sub";
return "";
}
public String  _explorer2(boolean _darktheme) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
int _marginbord = 0;
int _margin = 0;
int _margincartouche = 0;
int _hauteurcartouche = 0;
int _largeurbtn = 0;
int _id = 0;
int _largeur = 0;
int _hauteur = 0;
 //BA.debugLineNum = 355;BA.debugLine="Public Sub Explorer2(DarkTheme As Boolean)";
 //BA.debugLineNum = 359;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 360;BA.debugLine="Dim MarginBord As Int: MarginBord = 19dip";
_marginbord = 0;
 //BA.debugLineNum = 360;BA.debugLine="Dim MarginBord As Int: MarginBord = 19dip";
_marginbord = __c.DipToCurrent((int) (19));
 //BA.debugLineNum = 361;BA.debugLine="Dim Margin As Int: Margin = 4dip";
_margin = 0;
 //BA.debugLineNum = 361;BA.debugLine="Dim Margin As Int: Margin = 4dip";
_margin = __c.DipToCurrent((int) (4));
 //BA.debugLineNum = 362;BA.debugLine="Dim MarginCartouche As Int: MarginCartouche = 4di";
_margincartouche = 0;
 //BA.debugLineNum = 362;BA.debugLine="Dim MarginCartouche As Int: MarginCartouche = 4di";
_margincartouche = __c.DipToCurrent((int) (4));
 //BA.debugLineNum = 363;BA.debugLine="Dim HauteurCartouche As Int: HauteurCartouche = 5";
_hauteurcartouche = 0;
 //BA.debugLineNum = 363;BA.debugLine="Dim HauteurCartouche As Int: HauteurCartouche = 5";
_hauteurcartouche = __c.DipToCurrent((int) (50));
 //BA.debugLineNum = 364;BA.debugLine="Dim LargeurBtn As Int: LargeurBtn = 70dip";
_largeurbtn = 0;
 //BA.debugLineNum = 364;BA.debugLine="Dim LargeurBtn As Int: LargeurBtn = 70dip";
_largeurbtn = __c.DipToCurrent((int) (70));
 //BA.debugLineNum = 366;BA.debugLine="pnlMasque.Initialize(\"\")";
_pnlmasque.Initialize(ba,"");
 //BA.debugLineNum = 367;BA.debugLine="Dim id As Int";
_id = 0;
 //BA.debugLineNum = 368;BA.debugLine="If DarkTheme Then";
if (_darktheme) { 
 //BA.debugLineNum = 369;BA.debugLine="id = r.GetStaticField(\"android.R$drawable\", \"ale";
_id = (int)(BA.ObjectToNumber(_r.GetStaticField("android.R$drawable","alert_dark_frame")));
 }else {
 //BA.debugLineNum = 371;BA.debugLine="id = r.GetStaticField(\"android.R$drawable\", \"ale";
_id = (int)(BA.ObjectToNumber(_r.GetStaticField("android.R$drawable","alert_light_frame")));
 };
 //BA.debugLineNum = 373;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(ba));
 //BA.debugLineNum = 374;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
_r.Target = _r.RunMethod("getResources");
 //BA.debugLineNum = 375;BA.debugLine="pnlMasque.Background = r.RunMethod2(\"getDrawable\"";
_pnlmasque.setBackground((android.graphics.drawable.Drawable)(_r.RunMethod2("getDrawable",BA.NumberToString(_id),"java.lang.int")));
 //BA.debugLineNum = 376;BA.debugLine="r.Target = pnlMasque";
_r.Target = (Object)(_pnlmasque.getObject());
 //BA.debugLineNum = 377;BA.debugLine="r.SetOnTouchListener(\"pnl_BlockTouch\")";
_r.SetOnTouchListener(ba,"pnl_BlockTouch");
 //BA.debugLineNum = 379;BA.debugLine="BackgroundColor = Colors.Transparent";
_backgroundcolor = __c.Colors.Transparent;
 //BA.debugLineNum = 380;BA.debugLine="pnlFiles.Initialize(\"\")";
_pnlfiles.Initialize(ba,"");
 //BA.debugLineNum = 381;BA.debugLine="pnlFiles.Color = BackgroundColor";
_pnlfiles.setColor(_backgroundcolor);
 //BA.debugLineNum = 382;BA.debugLine="svFichiers.Initialize(-1, 0, \"SVF\")";
_svfichiers.Initialize(ba,(int) (-1),(int) (0),"SVF");
 //BA.debugLineNum = 383;BA.debugLine="svFichiers.Color = BackgroundColor";
_svfichiers.setColor(_backgroundcolor);
 //BA.debugLineNum = 384;BA.debugLine="Dim Largeur, Hauteur As Int";
_largeur = 0;
_hauteur = 0;
 //BA.debugLineNum = 385;BA.debugLine="Largeur = 100%x - (2*MarginBord)";
_largeur = (int) (__c.PerXToCurrent((float) (100),ba)-(2*_marginbord));
 //BA.debugLineNum = 386;BA.debugLine="Hauteur = 100%y - (2*MarginBord)";
_hauteur = (int) (__c.PerYToCurrent((float) (100),ba)-(2*_marginbord));
 //BA.debugLineNum = 387;BA.debugLine="pnlFiles.AddView(svFichiers, Margin, Margin, Larg";
_pnlfiles.AddView((android.view.View)(_svfichiers.getObject()),_margin,_margin,(int) (_largeur-(2*_margin)),(int) (_hauteur-(2*_margin)-_hauteurcartouche));
 //BA.debugLineNum = 388;BA.debugLine="r.Target = svFichiers";
_r.Target = (Object)(_svfichiers.getObject());
 //BA.debugLineNum = 389;BA.debugLine="r.SetOnKeyListener(\"dlg_KeyPress\")";
_r.SetOnKeyListener(ba,"dlg_KeyPress");
 //BA.debugLineNum = 390;BA.debugLine="r.SetOnFocusListener(\"dlg_HasFocus\")";
_r.SetOnFocusListener(ba,"dlg_HasFocus");
 //BA.debugLineNum = 391;BA.debugLine="If DarkTheme Then";
if (_darktheme) { 
 //BA.debugLineNum = 392;BA.debugLine="FolderTextColor = Colors.White";
_foldertextcolor = __c.Colors.White;
 //BA.debugLineNum = 393;BA.debugLine="FileTextColor1 = Colors.ARGB(220, 255, 255, 255)";
_filetextcolor1 = __c.Colors.ARGB((int) (220),(int) (255),(int) (255),(int) (255));
 //BA.debugLineNum = 394;BA.debugLine="FileTextColor2 = Colors.ARGB(128, 255, 255, 255)";
_filetextcolor2 = __c.Colors.ARGB((int) (128),(int) (255),(int) (255),(int) (255));
 //BA.debugLineNum = 395;BA.debugLine="DividerColor = Colors.DarkGray";
_dividercolor = __c.Colors.DarkGray;
 }else {
 //BA.debugLineNum = 397;BA.debugLine="FolderTextColor = Colors.Black";
_foldertextcolor = __c.Colors.Black;
 //BA.debugLineNum = 398;BA.debugLine="FileTextColor1 = Colors.ARGB(200, 0, 0, 0)";
_filetextcolor1 = __c.Colors.ARGB((int) (200),(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 399;BA.debugLine="FileTextColor2 = Colors.ARGB(128, 0, 0, 0)";
_filetextcolor2 = __c.Colors.ARGB((int) (128),(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 400;BA.debugLine="DividerColor = Colors.LightGray";
_dividercolor = __c.Colors.LightGray;
 };
 //BA.debugLineNum = 403;BA.debugLine="pnlCartouche.Initialize(\"\")";
_pnlcartouche.Initialize(ba,"");
 //BA.debugLineNum = 404;BA.debugLine="pnlCartouche.Color = Colors.Transparent";
_pnlcartouche.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 405;BA.debugLine="edtFilename.Initialize(\"\")";
_edtfilename.Initialize(ba,"");
 //BA.debugLineNum = 406;BA.debugLine="edtFilename.Enabled=False";
_edtfilename.setEnabled(__c.False);
 //BA.debugLineNum = 407;BA.debugLine="edtFilename.TextSize = 16";
_edtfilename.setTextSize((float) (16));
 //BA.debugLineNum = 408;BA.debugLine="edtFilename.InputType = Bit.Or(edtFilename.InputT";
_edtfilename.setInputType(__c.Bit.Or(_edtfilename.getInputType(),(int) (0x80000)));
 //BA.debugLineNum = 409;BA.debugLine="edtFilename.SingleLine = True";
_edtfilename.setSingleLine(__c.True);
 //BA.debugLineNum = 410;BA.debugLine="edtFilename.Wrap = False";
_edtfilename.setWrap(__c.False);
 //BA.debugLineNum = 411;BA.debugLine="r.Target = edtFilename";
_r.Target = (Object)(_edtfilename.getObject());
 //BA.debugLineNum = 412;BA.debugLine="r.SetOnKeyListener(\"dlg_KeyPress\")";
_r.SetOnKeyListener(ba,"dlg_KeyPress");
 //BA.debugLineNum = 413;BA.debugLine="r.SetOnFocusListener(\"dlg_HasFocus\")";
_r.SetOnFocusListener(ba,"dlg_HasFocus");
 //BA.debugLineNum = 414;BA.debugLine="ime1.SetCustomFilter(edtFilename,edtFilename.INPU";
_ime1.SetCustomFilter((android.widget.EditText)(_edtfilename.getObject()),_edtfilename.INPUT_TYPE_TEXT,"qwertyuiopasdfghjklñzxcvbnm-_0123456789QWERTYUIOPASDFGHJKLÑZXCVBNM");
 //BA.debugLineNum = 415;BA.debugLine="ime1.SetLengthFilter(edtFilename,20)";
_ime1.SetLengthFilter((android.widget.EditText)(_edtfilename.getObject()),(int) (20));
 //BA.debugLineNum = 416;BA.debugLine="pnlCartouche.AddView(edtFilename, MarginCartouche";
_pnlcartouche.AddView((android.view.View)(_edtfilename.getObject()),(int) (_margincartouche+__c.DipToCurrent((int) (1))),_margincartouche,(int) (_largeur-(3*_margincartouche)-_largeurbtn),(int) (_hauteurcartouche-_margincartouche));
 //BA.debugLineNum = 417;BA.debugLine="btnOK.Initialize(\"btnOK\")";
_btnok.Initialize(ba,"btnOK");
 //BA.debugLineNum = 418;BA.debugLine="btnOK.Text = strBtnOKTxt";
_btnok.setText((Object)(_strbtnoktxt));
 //BA.debugLineNum = 419;BA.debugLine="pnlCartouche.AddView(btnOK, edtFilename.Width + (";
_pnlcartouche.AddView((android.view.View)(_btnok.getObject()),(int) (_edtfilename.getWidth()+(2*_margincartouche)),_margincartouche,_largeurbtn,(int) (_hauteurcartouche-_margincartouche));
 //BA.debugLineNum = 421;BA.debugLine="pnlMasque.AddView(pnlFiles, MarginBord, MarginBor";
_pnlmasque.AddView((android.view.View)(_pnlfiles.getObject()),_marginbord,(int) (_marginbord-_margin),_largeur,(int) (_hauteur-_hauteurcartouche));
 //BA.debugLineNum = 422;BA.debugLine="pnlMasque.AddView(pnlCartouche, MarginBord, Haute";
_pnlmasque.AddView((android.view.View)(_pnlcartouche.getObject()),_marginbord,(int) (_hauteur-_hauteurcartouche+_pnlfiles.getTop()),_largeur,_hauteurcartouche);
 //BA.debugLineNum = 423;BA.debugLine="actEcran.AddView(pnlMasque, 0, 0, 100%x, 100%y)";
_actecran.AddView((android.view.View)(_pnlmasque.getObject()),(int) (0),(int) (0),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 //BA.debugLineNum = 425;BA.debugLine="If strChemin.EndsWith(\"/\") And strChemin <> \"/\" T";
if (_strchemin.endsWith("/") && (_strchemin).equals("/") == false) { 
_strchemin = _strchemin.substring((int) (0),_strchemin.length());};
 //BA.debugLineNum = 426;BA.debugLine="ReadFolder(strChemin)";
_readfolder(_strchemin);
 //BA.debugLineNum = 427;BA.debugLine="CommonExplorer";
_commonexplorer();
 //BA.debugLineNum = 428;BA.debugLine="End Sub";
return "";
}
public String  _explorermulti() throws Exception{
 //BA.debugLineNum = 337;BA.debugLine="Public Sub ExplorerMulti";
 //BA.debugLineNum = 338;BA.debugLine="bMultiFolderSelection = bOnlyFolders";
_bmultifolderselection = _bonlyfolders;
 //BA.debugLineNum = 339;BA.debugLine="bMultiFileSelection = Not(bOnlyFolders)";
_bmultifileselection = __c.Not(_bonlyfolders);
 //BA.debugLineNum = 340;BA.debugLine="Explorer";
_explorer();
 //BA.debugLineNum = 341;BA.debugLine="End Sub";
return "";
}
public String  _explorermulti2(boolean _darktheme) throws Exception{
 //BA.debugLineNum = 442;BA.debugLine="Public Sub ExplorerMulti2(DarkTheme As Boolean)";
 //BA.debugLineNum = 443;BA.debugLine="bMultiFolderSelection = bOnlyFolders";
_bmultifolderselection = _bonlyfolders;
 //BA.debugLineNum = 444;BA.debugLine="bMultiFileSelection = Not(bOnlyFolders)";
_bmultifileselection = __c.Not(_bonlyfolders);
 //BA.debugLineNum = 445;BA.debugLine="Explorer2(DarkTheme)";
_explorer2(_darktheme);
 //BA.debugLineNum = 446;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _activity,String _defaultfolder,String _filter,boolean _visupnl,boolean _onlyfolders,String _oktext) throws Exception{
innerInitialize(_ba);
int _ecart = 0;
String _strfiltre = "";
int _posvirg = 0;
 //BA.debugLineNum = 60;BA.debugLine="Public Sub Initialize(Activity As Activity, Defaul";
 //BA.debugLineNum = 61;BA.debugLine="ime1.Initialize(\"\")";
_ime1.Initialize("");
 //BA.debugLineNum = 62;BA.debugLine="Dim Ecart As Int: Ecart = 10dip";
_ecart = 0;
 //BA.debugLineNum = 62;BA.debugLine="Dim Ecart As Int: Ecart = 10dip";
_ecart = __c.DipToCurrent((int) (10));
 //BA.debugLineNum = 63;BA.debugLine="actEcran = Activity";
_actecran = _activity;
 //BA.debugLineNum = 64;BA.debugLine="strChemin = DefaultFolder";
_strchemin = _defaultfolder;
 //BA.debugLineNum = 65;BA.debugLine="lstFiltre.Initialize";
_lstfiltre.Initialize();
 //BA.debugLineNum = 66;BA.debugLine="Dim strFiltre As String, PosVirg As Int";
_strfiltre = "";
_posvirg = 0;
 //BA.debugLineNum = 67;BA.debugLine="strFiltre = Filter.ToLowerCase";
_strfiltre = _filter.toLowerCase();
 //BA.debugLineNum = 68;BA.debugLine="Do While strFiltre.Contains(\",\")";
while (_strfiltre.contains(",")) {
 //BA.debugLineNum = 69;BA.debugLine="PosVirg = strFiltre.IndexOf(\",\")";
_posvirg = _strfiltre.indexOf(",");
 //BA.debugLineNum = 70;BA.debugLine="lstFiltre.Add(strFiltre.SubString2(0, PosVirg).T";
_lstfiltre.Add((Object)(_strfiltre.substring((int) (0),_posvirg).trim()));
 //BA.debugLineNum = 71;BA.debugLine="strFiltre = strFiltre.SubString(PosVirg + 1)";
_strfiltre = _strfiltre.substring((int) (_posvirg+1));
 }
;
 //BA.debugLineNum = 73;BA.debugLine="lstFiltre.Add(strFiltre.Trim)";
_lstfiltre.Add((Object)(_strfiltre.trim()));
 //BA.debugLineNum = 74;BA.debugLine="bOnlyFolders = OnlyFolders";
_bonlyfolders = _onlyfolders;
 //BA.debugLineNum = 75;BA.debugLine="bVisualiser = VisuPnl";
_bvisualiser = _visupnl;
 //BA.debugLineNum = 76;BA.debugLine="bMultiFolderSelection = False";
_bmultifolderselection = __c.False;
 //BA.debugLineNum = 77;BA.debugLine="bMultiFileSelection = False";
_bmultifileselection = __c.False;
 //BA.debugLineNum = 78;BA.debugLine="strBtnOKTxt = OkText";
_strbtnoktxt = _oktext;
 //BA.debugLineNum = 79;BA.debugLine="FastScrollEnabled = False";
_fastscrollenabled = __c.False;
 //BA.debugLineNum = 80;BA.debugLine="Ellipsis = True";
_ellipsis = __c.True;
 //BA.debugLineNum = 81;BA.debugLine="BorderColor = Colors.RGB(25, 90, 179)";
_bordercolor = __c.Colors.RGB((int) (25),(int) (90),(int) (179));
 //BA.debugLineNum = 82;BA.debugLine="BackgroundColor = Colors.RGB(19, 27, 67)";
_backgroundcolor = __c.Colors.RGB((int) (19),(int) (27),(int) (67));
 //BA.debugLineNum = 83;BA.debugLine="FolderTextColor = Colors.White";
_foldertextcolor = __c.Colors.White;
 //BA.debugLineNum = 84;BA.debugLine="FileTextColor1 = Colors.RGB(116, 172, 232)";
_filetextcolor1 = __c.Colors.RGB((int) (116),(int) (172),(int) (232));
 //BA.debugLineNum = 85;BA.debugLine="FileTextColor2 = Colors.Gray";
_filetextcolor2 = __c.Colors.Gray;
 //BA.debugLineNum = 86;BA.debugLine="DividerColor = Colors.DarkGray";
_dividercolor = __c.Colors.DarkGray;
 //BA.debugLineNum = 87;BA.debugLine="DialogRect.Initialize(Ecart, Ecart, 100%x - Ecart";
_dialogrect.Initialize(_ecart,_ecart,(int) (__c.PerXToCurrent((float) (100),ba)-_ecart),(int) (__c.PerYToCurrent((float) (100),ba)-_ecart));
 //BA.debugLineNum = 88;BA.debugLine="WaitUntilOK = True";
_waituntilok = __c.True;
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public String  _initializefilelist() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Private Sub InitializeFileList";
 //BA.debugLineNum = 108;BA.debugLine="lstFichiers.Initialize(Me, svFichiers, \"\", \"lstFi";
_lstfichiers._initialize(ba,this,_svfichiers,"","lstFichiers_Click","lstFichiers_LongClick",__c.DipToCurrent((int) (1)));
 //BA.debugLineNum = 109;BA.debugLine="lstFichiers.BackgroundColor = BackgroundColor";
_lstfichiers._backgroundcolor = _backgroundcolor;
 //BA.debugLineNum = 110;BA.debugLine="lstFichiers.DividerColor = DividerColor";
_lstfichiers._dividercolor = _dividercolor;
 //BA.debugLineNum = 111;BA.debugLine="svFichiers.VerticalScrollPosition = 0";
_svfichiers.setVerticalScrollPosition((int) (0));
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return "";
}
public String  _initializescrollpanel() throws Exception{
int _spwidth = 0;
int _spheight = 0;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 1051;BA.debugLine="Private Sub InitializeScrollPanel";
 //BA.debugLineNum = 1052;BA.debugLine="Dim spWidth As Int = 64dip";
_spwidth = __c.DipToCurrent((int) (64));
 //BA.debugLineNum = 1053;BA.debugLine="Dim spHeight As Int = 52dip";
_spheight = __c.DipToCurrent((int) (52));
 //BA.debugLineNum = 1055;BA.debugLine="pnlRange.Initialize(\"\")";
_pnlrange.Initialize(ba,"");
 //BA.debugLineNum = 1056;BA.debugLine="pnlFiles.AddView(pnlRange, svFichiers.Left + svFi";
_pnlfiles.AddView((android.view.View)(_pnlrange.getObject()),(int) (_svfichiers.getLeft()+_svfichiers.getWidth()-_spwidth),_svfichiers.getTop(),_spwidth,_svfichiers.getHeight());
 //BA.debugLineNum = 1057;BA.debugLine="pnlRange.Visible = False";
_pnlrange.setVisible(__c.False);
 //BA.debugLineNum = 1058;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 1059;BA.debugLine="r.Target = pnlRange";
_r.Target = (Object)(_pnlrange.getObject());
 //BA.debugLineNum = 1060;BA.debugLine="r.SetOnTouchListener(\"SP_Touch\")";
_r.SetOnTouchListener(ba,"SP_Touch");
 //BA.debugLineNum = 1062;BA.debugLine="pnlDisplay.Initialize(\"\")";
_pnldisplay.Initialize(ba,"");
 //BA.debugLineNum = 1063;BA.debugLine="pnlRange.AddView(pnlDisplay, 0, 0, spWidth, spHei";
_pnlrange.AddView((android.view.View)(_pnldisplay.getObject()),(int) (0),(int) (0),_spwidth,_spheight);
 //BA.debugLineNum = 1064;BA.debugLine="pnlDisplay.Background = LoadDrawable(\"scrollbar_h";
_pnldisplay.setBackground((android.graphics.drawable.Drawable)(_loaddrawable("scrollbar_handle_accelerated_anim2")));
 //BA.debugLineNum = 1066;BA.debugLine="r.Target = r.RunStaticMethod(\"android.view.ViewCo";
_r.Target = _r.RunStaticMethod("android.view.ViewConfiguration","get",new Object[]{(Object)(_r.GetContext(ba))},new String[]{"android.content.Context"});
 //BA.debugLineNum = 1067;BA.debugLine="Duration = r.RunMethod(\"getScrollDefaultDelay\") +";
_duration = (int) ((double)(BA.ObjectToNumber(_r.RunMethod("getScrollDefaultDelay")))+__c.Bit.ShiftRight((int)(BA.ObjectToNumber(_r.RunMethod("getScrollBarFadeDuration"))),(int) (1)));
 //BA.debugLineNum = 1068;BA.debugLine="TimeOut.Initialize(\"TimeOut\", 0)";
_timeout.Initialize(ba,"TimeOut",(long) (0));
 //BA.debugLineNum = 1069;BA.debugLine="Anim.InitializeTranslate(\"Anim\", 0, 0, spWidth, 0";
_anim.InitializeTranslate(ba,"Anim",(float) (0),(float) (0),(float) (_spwidth),(float) (0));
 //BA.debugLineNum = 1070;BA.debugLine="Anim.Duration = Duration";
_anim.setDuration((long) (_duration));
 //BA.debugLineNum = 1071;BA.debugLine="Anim.RepeatCount = 0";
_anim.setRepeatCount((int) (0));
 //BA.debugLineNum = 1073;BA.debugLine="MaxPos = pnlRange.Height - pnlDisplay.Height";
_maxpos = (int) (_pnlrange.getHeight()-_pnldisplay.getHeight());
 //BA.debugLineNum = 1074;BA.debugLine="bUserMovingPnl = False 'Becomes True when the use";
_busermovingpnl = __c.False;
 //BA.debugLineNum = 1075;BA.debugLine="bWaitForScroll = True";
_bwaitforscroll = __c.True;
 //BA.debugLineNum = 1076;BA.debugLine="pnlDisplay.Top = CalcNewTop";
_pnldisplay.setTop(_calcnewtop());
 //BA.debugLineNum = 1077;BA.debugLine="End Sub";
return "";
}
public boolean  _isactive() throws Exception{
 //BA.debugLineNum = 503;BA.debugLine="Public Sub IsActive As Boolean";
 //BA.debugLineNum = 504;BA.debugLine="Return pnlMasque.IsInitialized";
if (true) return _pnlmasque.IsInitialized();
 //BA.debugLineNum = 505;BA.debugLine="End Sub";
return false;
}
public boolean  _isimage(String _nomfichier) throws Exception{
String _minus = "";
 //BA.debugLineNum = 514;BA.debugLine="Private Sub IsImage(NomFichier As String) As Boole";
 //BA.debugLineNum = 515;BA.debugLine="Dim Minus As String";
_minus = "";
 //BA.debugLineNum = 516;BA.debugLine="Minus = NomFichier.ToLowerCase";
_minus = _nomfichier.toLowerCase();
 //BA.debugLineNum = 517;BA.debugLine="Return (Minus.EndsWith(\".bmp\") Or Minus.EndsWith(";
if (true) return (_minus.endsWith(".bmp") || _minus.endsWith(".gif") || _minus.endsWith(".jpg") || _minus.endsWith(".png"));
 //BA.debugLineNum = 518;BA.debugLine="End Sub";
return false;
}
public boolean  _istext(String _nomfichier) throws Exception{
String _minus = "";
 //BA.debugLineNum = 585;BA.debugLine="Private Sub IsText(NomFichier As String) As Boolea";
 //BA.debugLineNum = 586;BA.debugLine="Dim Minus As String";
_minus = "";
 //BA.debugLineNum = 587;BA.debugLine="Minus = NomFichier.ToLowerCase";
_minus = _nomfichier.toLowerCase();
 //BA.debugLineNum = 588;BA.debugLine="Return (Minus.EndsWith(\".css\") Or Minus.EndsWith(";
if (true) return (_minus.endsWith(".css") || _minus.endsWith(".htm") || _minus.endsWith(".html") || _minus.endsWith(".txt") || _minus.endsWith(".xml"));
 //BA.debugLineNum = 590;BA.debugLine="End Sub";
return false;
}
public Object  _loaddrawable(String _name) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
int _id_drawable = 0;
 //BA.debugLineNum = 1080;BA.debugLine="Private Sub LoadDrawable(Name As String) As Object";
 //BA.debugLineNum = 1081;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 1082;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(ba));
 //BA.debugLineNum = 1083;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
_r.Target = _r.RunMethod("getResources");
 //BA.debugLineNum = 1084;BA.debugLine="r.Target = r.RunMethod(\"getSystem\")";
_r.Target = _r.RunMethod("getSystem");
 //BA.debugLineNum = 1085;BA.debugLine="Dim ID_Drawable As Int";
_id_drawable = 0;
 //BA.debugLineNum = 1086;BA.debugLine="ID_Drawable = r.RunMethod4(\"getIdentifier\", Array";
_id_drawable = (int)(BA.ObjectToNumber(_r.RunMethod4("getIdentifier",new Object[]{(Object)(_name),(Object)("drawable"),(Object)("android")},new String[]{"java.lang.String","java.lang.String","java.lang.String"})));
 //BA.debugLineNum = 1088;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(ba));
 //BA.debugLineNum = 1089;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
_r.Target = _r.RunMethod("getResources");
 //BA.debugLineNum = 1090;BA.debugLine="Return r.RunMethod2(\"getDrawable\", ID_Drawable, \"";
if (true) return _r.RunMethod2("getDrawable",BA.NumberToString(_id_drawable),"java.lang.int");
 //BA.debugLineNum = 1091;BA.debugLine="End Sub";
return null;
}
public String  _lstfichiers_click(anywheresoftware.b4a.objects.PanelWrapper _item,Object _itemtag) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _posslash = 0;
String _parentpath = "";
String _newpath = "";
anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbx = null;
String _aux = "";
 //BA.debugLineNum = 631;BA.debugLine="Private Sub lstFichiers_Click(Item As Panel, ItemT";
 //BA.debugLineNum = 632;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 633;BA.debugLine="If Item.GetView(0) Is CheckBox Then";
if (_item.GetView((int) (0)).getObjectOrNull() instanceof android.widget.CheckBox) { 
 //BA.debugLineNum = 634;BA.debugLine="lbl = Item.GetView(1)";
_lbl.setObject((android.widget.TextView)(_item.GetView((int) (1)).getObject()));
 }else {
 //BA.debugLineNum = 636;BA.debugLine="lbl = Item.GetView(0)";
_lbl.setObject((android.widget.TextView)(_item.GetView((int) (0)).getObject()));
 };
 //BA.debugLineNum = 639;BA.debugLine="If lbl.Text = \"MODELO\" Then";
if ((_lbl.getText()).equals("MODELO")) { 
 //BA.debugLineNum = 641;BA.debugLine="Dim PosSlash As Int, ParentPath As String";
_posslash = 0;
_parentpath = "";
 //BA.debugLineNum = 642;BA.debugLine="PosSlash = strChemin.LastIndexOf(\"/\")";
_posslash = _strchemin.lastIndexOf("/");
 //BA.debugLineNum = 643;BA.debugLine="ParentPath = strChemin.SubString2(0, PosSlash)";
_parentpath = _strchemin.substring((int) (0),_posslash);
 //BA.debugLineNum = 644;BA.debugLine="If ParentPath = \"\" Then ParentPath = \"/\"";
if ((_parentpath).equals("")) { 
_parentpath = "/";};
 //BA.debugLineNum = 645;BA.debugLine="ReadFolder(ParentPath)";
_readfolder(_parentpath);
 //BA.debugLineNum = 646;BA.debugLine="If bOnlyFolders Then";
if (_bonlyfolders) { 
 //BA.debugLineNum = 647;BA.debugLine="edtFilename.Text = ParentPath";
_edtfilename.setText((Object)(_parentpath));
 //BA.debugLineNum = 648;BA.debugLine="edtFilename.RequestFocus";
_edtfilename.RequestFocus();
 }else {
 //BA.debugLineNum = 650;BA.debugLine="edtFilename.Text = \"\"";
_edtfilename.setText((Object)(""));
 };
 }else if(_lbl.getText().startsWith("/ ")) { 
 //BA.debugLineNum = 654;BA.debugLine="Dim NewPath As String";
_newpath = "";
 //BA.debugLineNum = 655;BA.debugLine="If strChemin = \"/\" Then";
if ((_strchemin).equals("/")) { 
 //BA.debugLineNum = 656;BA.debugLine="NewPath = strChemin & lbl.Text.SubString(2)";
_newpath = _strchemin+_lbl.getText().substring((int) (2));
 }else {
 //BA.debugLineNum = 658;BA.debugLine="NewPath = strChemin & \"/\" & lbl.Text.SubString(";
_newpath = _strchemin+"/"+_lbl.getText().substring((int) (2));
 };
 //BA.debugLineNum = 660;BA.debugLine="ReadFolder(NewPath)";
_readfolder(_newpath);
 //BA.debugLineNum = 661;BA.debugLine="If bOnlyFolders Then";
if (_bonlyfolders) { 
 //BA.debugLineNum = 662;BA.debugLine="edtFilename.Text = NewPath";
_edtfilename.setText((Object)(_newpath));
 //BA.debugLineNum = 663;BA.debugLine="edtFilename.SelectionStart = edtFilename.Text.L";
_edtfilename.setSelectionStart(_edtfilename.getText().length());
 //BA.debugLineNum = 664;BA.debugLine="edtFilename.RequestFocus";
_edtfilename.RequestFocus();
 }else {
 //BA.debugLineNum = 666;BA.debugLine="edtFilename.Text = \"\"";
_edtfilename.setText((Object)(""));
 };
 }else {
 //BA.debugLineNum = 670;BA.debugLine="If bVisualiser Then";
if (_bvisualiser) { 
 //BA.debugLineNum = 671;BA.debugLine="If IsImage(lbl.Text) Then";
if (_isimage(_lbl.getText())) { 
 //BA.debugLineNum = 672;BA.debugLine="AfficherImage(lbl.Text)";
_afficherimage(_lbl.getText());
 }else if(_istext(_lbl.getText())) { 
 //BA.debugLineNum = 674;BA.debugLine="AfficherTexte(lbl.Text)";
_affichertexte(_lbl.getText());
 };
 };
 //BA.debugLineNum = 677;BA.debugLine="If bMultiFileSelection Then";
if (_bmultifileselection) { 
 //BA.debugLineNum = 678;BA.debugLine="Dim cbx As CheckBox";
_cbx = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 679;BA.debugLine="cbx = Item.GetView(0)";
_cbx.setObject((android.widget.CheckBox)(_item.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 680;BA.debugLine="cbx.Checked = True";
_cbx.setChecked(__c.True);
 }else {
 //BA.debugLineNum = 682;BA.debugLine="edtFilename.Text = lbl.Text";
_edtfilename.setText((Object)(_lbl.getText()));
 };
 //BA.debugLineNum = 684;BA.debugLine="edtFilename.SelectionStart = edtFilename.Text.Le";
_edtfilename.setSelectionStart(_edtfilename.getText().length());
 //BA.debugLineNum = 685;BA.debugLine="edtFilename.RequestFocus";
_edtfilename.RequestFocus();
 };
 //BA.debugLineNum = 687;BA.debugLine="nombre=lbl.Text";
_nombre = _lbl.getText();
 //BA.debugLineNum = 689;BA.debugLine="Log(edtFilename.text)";
__c.Log(_edtfilename.getText());
 //BA.debugLineNum = 690;BA.debugLine="edtFilename.Text=nombre.SubString(11)";
_edtfilename.setText((Object)(_nombre.substring((int) (11))));
 //BA.debugLineNum = 691;BA.debugLine="Log(edtFilename.text)";
__c.Log(_edtfilename.getText());
 //BA.debugLineNum = 693;BA.debugLine="WaitUntilOK = False";
_waituntilok = __c.False;
 //BA.debugLineNum = 695;BA.debugLine="Dim aux As String";
_aux = "";
 //BA.debugLineNum = 697;BA.debugLine="If	 File.Exists(File.DirRootExternal&strChemin.Su";
if (__c.File.Exists(__c.File.getDirRootExternal()+_strchemin.substring((int) (11)),_selection.ChosenFile)) { 
 //BA.debugLineNum = 698;BA.debugLine="aux=edtFilename.text";
_aux = _edtfilename.getText();
 //BA.debugLineNum = 700;BA.debugLine="ToastMessageShow(aux,True)";
__c.ToastMessageShow(_aux,__c.True);
 //BA.debugLineNum = 701;BA.debugLine="If strChemin.SubString(20)=\"F1A\" Then";
if ((_strchemin.substring((int) (20))).equals("F1A")) { 
 //BA.debugLineNum = 702;BA.debugLine="F1A.strfichero=aux";
_f1a._strfichero = _aux;
 //BA.debugLineNum = 708;BA.debugLine="If aux.Contains(\"PRUEBA\") Then";
if (_aux.contains("PRUEBA")) { 
 //BA.debugLineNum = 709;BA.debugLine="F1A.strspinner=\"PRUEBA\"";
_f1a._strspinner = "PRUEBA";
 };
 //BA.debugLineNum = 711;BA.debugLine="If aux.Contains(\"PRIMERA HORA\") Then";
if (_aux.contains("PRIMERA HORA")) { 
 //BA.debugLineNum = 712;BA.debugLine="F1A.strspinner=\"PRIMERA HORA\"";
_f1a._strspinner = "PRIMERA HORA";
 };
 //BA.debugLineNum = 714;BA.debugLine="If aux.Contains(\"VIENTO ALTO\") Then";
if (_aux.contains("VIENTO ALTO")) { 
 //BA.debugLineNum = 715;BA.debugLine="F1A.strspinner=\"VIENTO ALTO\"";
_f1a._strspinner = "VIENTO ALTO";
 };
 //BA.debugLineNum = 717;BA.debugLine="If aux.Contains(\"VIENTO MEDIO\") Then";
if (_aux.contains("VIENTO MEDIO")) { 
 //BA.debugLineNum = 718;BA.debugLine="F1A.strspinner=\"VIENTO MEDIO\"";
_f1a._strspinner = "VIENTO MEDIO";
 };
 //BA.debugLineNum = 720;BA.debugLine="If aux.Contains(\"CALMA\") Then";
if (_aux.contains("CALMA")) { 
 //BA.debugLineNum = 721;BA.debugLine="F1A.strspinner=\"CALMA\"";
_f1a._strspinner = "CALMA";
 };
 //BA.debugLineNum = 723;BA.debugLine="If aux.Contains(\"TERMICA\") Then";
if (_aux.contains("TERMICA")) { 
 //BA.debugLineNum = 724;BA.debugLine="F1A.strspinner=\"TERMICA\"";
_f1a._strspinner = "TERMICA";
 };
 //BA.debugLineNum = 726;BA.debugLine="CallSub(F1A,\"AbrirFichero\")";
__c.CallSubNew(ba,(Object)(_f1a.getObject()),"AbrirFichero");
 };
 //BA.debugLineNum = 729;BA.debugLine="If strChemin.SubString(20)=\"F1B\" Then";
if ((_strchemin.substring((int) (20))).equals("F1B")) { 
 //BA.debugLineNum = 730;BA.debugLine="F1B.strfichero=aux";
_f1b._strfichero = _aux;
 //BA.debugLineNum = 734;BA.debugLine="If aux.Contains(\"PRUEBA\") Then";
if (_aux.contains("PRUEBA")) { 
 //BA.debugLineNum = 735;BA.debugLine="F1B.strspinner=\"PRUEBA\"";
_f1b._strspinner = "PRUEBA";
 };
 //BA.debugLineNum = 737;BA.debugLine="If aux.Contains(\"PRIMERA HORA\") Then";
if (_aux.contains("PRIMERA HORA")) { 
 //BA.debugLineNum = 738;BA.debugLine="F1B.strspinner=\"PRIMERA HORA\"";
_f1b._strspinner = "PRIMERA HORA";
 };
 //BA.debugLineNum = 740;BA.debugLine="If aux.Contains(\"VIENTO ALTO\") Then";
if (_aux.contains("VIENTO ALTO")) { 
 //BA.debugLineNum = 741;BA.debugLine="F1B.strspinner=\"VIENTO ALTO\"";
_f1b._strspinner = "VIENTO ALTO";
 };
 //BA.debugLineNum = 743;BA.debugLine="If aux.Contains(\"VIENTO MEDIO\") Then";
if (_aux.contains("VIENTO MEDIO")) { 
 //BA.debugLineNum = 744;BA.debugLine="F1B.strspinner=\"VIENTO MEDIO\"";
_f1b._strspinner = "VIENTO MEDIO";
 };
 //BA.debugLineNum = 746;BA.debugLine="If aux.Contains(\"CALMA\") Then";
if (_aux.contains("CALMA")) { 
 //BA.debugLineNum = 747;BA.debugLine="F1B.strspinner=\"CALMA\"";
_f1b._strspinner = "CALMA";
 };
 //BA.debugLineNum = 749;BA.debugLine="If aux.Contains(\"TERMICA\") Then";
if (_aux.contains("TERMICA")) { 
 //BA.debugLineNum = 750;BA.debugLine="F1B.strspinner=\"TERMICA\"";
_f1b._strspinner = "TERMICA";
 };
 //BA.debugLineNum = 752;BA.debugLine="CallSub(F1B,\"AbrirFichero\")";
__c.CallSubNew(ba,(Object)(_f1b.getObject()),"AbrirFichero");
 };
 //BA.debugLineNum = 755;BA.debugLine="If strChemin.SubString(20)=\"F1C\" Then";
if ((_strchemin.substring((int) (20))).equals("F1C")) { 
 //BA.debugLineNum = 756;BA.debugLine="F1C.strfichero=aux";
_f1c._strfichero = _aux;
 //BA.debugLineNum = 760;BA.debugLine="If aux.Contains(\"PRUEBA\") Then";
if (_aux.contains("PRUEBA")) { 
 //BA.debugLineNum = 761;BA.debugLine="F1C.strspinner=\"PRUEBA\"";
_f1c._strspinner = "PRUEBA";
 };
 //BA.debugLineNum = 763;BA.debugLine="If aux.Contains(\"PRIMERA HORA\") Then";
if (_aux.contains("PRIMERA HORA")) { 
 //BA.debugLineNum = 764;BA.debugLine="F1C.strspinner=\"PRIMERA HORA\"";
_f1c._strspinner = "PRIMERA HORA";
 };
 //BA.debugLineNum = 766;BA.debugLine="If aux.Contains(\"VIENTO ALTO\") Then";
if (_aux.contains("VIENTO ALTO")) { 
 //BA.debugLineNum = 767;BA.debugLine="F1C.strspinner=\"VIENTO ALTO\"";
_f1c._strspinner = "VIENTO ALTO";
 };
 //BA.debugLineNum = 769;BA.debugLine="If aux.Contains(\"VIENTO MEDIO\") Then";
if (_aux.contains("VIENTO MEDIO")) { 
 //BA.debugLineNum = 770;BA.debugLine="F1C.strspinner=\"VIENTO MEDIO\"";
_f1c._strspinner = "VIENTO MEDIO";
 };
 //BA.debugLineNum = 772;BA.debugLine="If aux.Contains(\"CALMA\") Then";
if (_aux.contains("CALMA")) { 
 //BA.debugLineNum = 773;BA.debugLine="F1C.strspinner=\"CALMA\"";
_f1c._strspinner = "CALMA";
 };
 //BA.debugLineNum = 775;BA.debugLine="If aux.Contains(\"TERMICA\") Then";
if (_aux.contains("TERMICA")) { 
 //BA.debugLineNum = 776;BA.debugLine="F1C.strspinner=\"TERMICA\"";
_f1c._strspinner = "TERMICA";
 };
 //BA.debugLineNum = 778;BA.debugLine="CallSub(F1C,\"AbrirFichero\")";
__c.CallSubNew(ba,(Object)(_f1c.getObject()),"AbrirFichero");
 };
 };
 //BA.debugLineNum = 786;BA.debugLine="End Sub";
return "";
}
public String  _lstfichiers_longclick(anywheresoftware.b4a.objects.PanelWrapper _item,Object _itemtag) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
String _aux = "";
int _r = 0;
 //BA.debugLineNum = 788;BA.debugLine="Private Sub lstFichiers_LongClick(Item As Panel, I";
 //BA.debugLineNum = 789;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 790;BA.debugLine="Dim AUX As String";
_aux = "";
 //BA.debugLineNum = 791;BA.debugLine="If Item.GetView(0) Is CheckBox Then";
if (_item.GetView((int) (0)).getObjectOrNull() instanceof android.widget.CheckBox) { 
 //BA.debugLineNum = 792;BA.debugLine="lbl = Item.GetView(1)";
_lbl.setObject((android.widget.TextView)(_item.GetView((int) (1)).getObject()));
 }else {
 //BA.debugLineNum = 794;BA.debugLine="lbl = Item.GetView(0)";
_lbl.setObject((android.widget.TextView)(_item.GetView((int) (0)).getObject()));
 };
 //BA.debugLineNum = 800;BA.debugLine="If  F1A.strModelo=lbl.Text Or F1B.strModelo=lbl.";
if ((_f1a._strmodelo).equals(_lbl.getText()) || (_f1b._strmodelo).equals(_lbl.getText()) || (_f1c._strmodelo).equals(_lbl.getText())) { 
 //BA.debugLineNum = 801;BA.debugLine="ToastMessageShow(\"Fichero abierto, Por favor ci";
__c.ToastMessageShow("Fichero abierto, Por favor cierrelo",__c.True);
 }else {
 //BA.debugLineNum = 804;BA.debugLine="Dim r As Int";
_r = 0;
 //BA.debugLineNum = 816;BA.debugLine="ToastMessageShow(\"Entro\",True)";
__c.ToastMessageShow("Entro",__c.True);
 //BA.debugLineNum = 817;BA.debugLine="AUX=strChemin.SubString(11)";
_aux = _strchemin.substring((int) (11));
 //BA.debugLineNum = 818;BA.debugLine="Selection.ChosenPath = strChemin";
_selection.ChosenPath = _strchemin;
 //BA.debugLineNum = 819;BA.debugLine="Selection.ChosenFile = edtFilename.Text";
_selection.ChosenFile = _edtfilename.getText();
 //BA.debugLineNum = 820;BA.debugLine="edtFilename.Text=\"\"";
_edtfilename.setText((Object)(""));
 //BA.debugLineNum = 821;BA.debugLine="File.Delete(File.DirRootExternal&AUX,lbl.TEXT)";
__c.File.Delete(__c.File.getDirRootExternal()+_aux,_lbl.getText());
 //BA.debugLineNum = 822;BA.debugLine="If strChemin.EndsWith(\"/\") And strChemin <> \"/";
if (_strchemin.endsWith("/") && (_strchemin).equals("/") == false) { 
_strchemin = _strchemin.substring((int) (0),_strchemin.length());};
 //BA.debugLineNum = 823;BA.debugLine="ReadFolder(strChemin)";
_readfolder(_strchemin);
 //BA.debugLineNum = 824;BA.debugLine="CommonExplorer";
_commonexplorer();
 };
 //BA.debugLineNum = 828;BA.debugLine="End Sub";
return "";
}
public String  _lstmulti_checkedchange(boolean _checked) throws Exception{
anywheresoftware.b4a.objects.collections.List _l = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _i = 0;
 //BA.debugLineNum = 830;BA.debugLine="Private Sub lstMulti_CheckedChange(Checked As Bool";
 //BA.debugLineNum = 831;BA.debugLine="Dim L As List = lstFichiers.CheckedPanels";
_l = new anywheresoftware.b4a.objects.collections.List();
_l = _lstfichiers._checkedpanels();
 //BA.debugLineNum = 832;BA.debugLine="Dim pnl As Panel, lbl As Label";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 833;BA.debugLine="edtFilename.Text = \"\"";
_edtfilename.setText((Object)(""));
 //BA.debugLineNum = 834;BA.debugLine="For i = 0 To L.Size - 1";
{
final int step4 = 1;
final int limit4 = (int) (_l.getSize()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 835;BA.debugLine="If edtFilename.Text <> \"\" Then edtFilename.Text";
if ((_edtfilename.getText()).equals("") == false) { 
_edtfilename.setText((Object)(_edtfilename.getText()+";"));};
 //BA.debugLineNum = 836;BA.debugLine="pnl = L.Get(i)";
_pnl.setObject((android.view.ViewGroup)(_l.Get(_i)));
 //BA.debugLineNum = 837;BA.debugLine="lbl = pnl.GetView(1)";
_lbl.setObject((android.widget.TextView)(_pnl.GetView((int) (1)).getObject()));
 //BA.debugLineNum = 838;BA.debugLine="If lbl.Text.StartsWith(\"/ \") Then";
if (_lbl.getText().startsWith("/ ")) { 
 //BA.debugLineNum = 839;BA.debugLine="If strChemin = \"/\" Then";
if ((_strchemin).equals("/")) { 
 //BA.debugLineNum = 840;BA.debugLine="edtFilename.Text = edtFilename.Text & strChemi";
_edtfilename.setText((Object)(_edtfilename.getText()+_strchemin+_lbl.getText().substring((int) (2))));
 }else {
 //BA.debugLineNum = 842;BA.debugLine="edtFilename.Text = edtFilename.Text & strChemi";
_edtfilename.setText((Object)(_edtfilename.getText()+_strchemin+"/"+_lbl.getText().substring((int) (2))));
 };
 }else {
 //BA.debugLineNum = 845;BA.debugLine="edtFilename.Text = edtFilename.Text & lbl.Text";
_edtfilename.setText((Object)(_edtfilename.getText()+_lbl.getText()));
 };
 }
};
 //BA.debugLineNum = 848;BA.debugLine="edtFilename.SelectionStart = edtFilename.Text.Len";
_edtfilename.setSelectionStart(_edtfilename.getText().length());
 //BA.debugLineNum = 849;BA.debugLine="End Sub";
return "";
}
public boolean  _pnl_blocktouch(Object _viewtag,int _action,float _x,float _y,Object _motionevent) throws Exception{
 //BA.debugLineNum = 470;BA.debugLine="Private Sub pnl_BlockTouch(ViewTag As Object, Acti";
 //BA.debugLineNum = 471;BA.debugLine="Return True";
if (true) return __c.True;
 //BA.debugLineNum = 472;BA.debugLine="End Sub";
return false;
}
public String  _pnlvisu_close(Object _viewtag) throws Exception{
 //BA.debugLineNum = 508;BA.debugLine="Private Sub pnlVisu_Close(ViewTag As Object)";
 //BA.debugLineNum = 509;BA.debugLine="svFichiers.Visible = True";
_svfichiers.setVisible(__c.True);
 //BA.debugLineNum = 510;BA.debugLine="pnlVisu.RemoveView";
_pnlvisu.RemoveView();
 //BA.debugLineNum = 511;BA.debugLine="pnlVisu = Null";
_pnlvisu.setObject((android.view.ViewGroup)(__c.Null));
 //BA.debugLineNum = 512;BA.debugLine="End Sub";
return "";
}
public String  _readfolder(String _chemin) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
anywheresoftware.b4a.objects.collections.List _lstd = null;
anywheresoftware.b4a.objects.collections.List _lstf = null;
anywheresoftware.b4a.objects.LabelWrapper _lblwait = null;
int _i = 0;
String _nomfichier = "";
int _f = 0;
 //BA.debugLineNum = 164;BA.debugLine="Private Sub ReadFolder(Chemin As String)";
 //BA.debugLineNum = 165;BA.debugLine="Dim lst, lstD, lstF As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
_lstd = new anywheresoftware.b4a.objects.collections.List();
_lstf = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 166;BA.debugLine="Try";
try { //BA.debugLineNum = 167;BA.debugLine="lst = File.ListFiles(Chemin)";
_lst = __c.File.ListFiles(_chemin);
 } 
       catch (Exception e148) {
			ba.setLastException(e148); //BA.debugLineNum = 169;BA.debugLine="lst = Null";
_lst.setObject((java.util.List)(__c.Null));
 };
 //BA.debugLineNum = 171;BA.debugLine="If lst.IsInitialized Then";
if (_lst.IsInitialized()) { 
 //BA.debugLineNum = 172;BA.debugLine="InitializeFileList";
_initializefilelist();
 //BA.debugLineNum = 173;BA.debugLine="DoEvents";
__c.DoEvents();
 //BA.debugLineNum = 174;BA.debugLine="Dim lblWait As Label";
_lblwait = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 175;BA.debugLine="lblWait.Initialize(\"\")";
_lblwait.Initialize(ba,"");
 //BA.debugLineNum = 176;BA.debugLine="If lst.Size > 30 Then";
if (_lst.getSize()>30) { 
 //BA.debugLineNum = 178;BA.debugLine="lblWait.Gravity = Gravity.CENTER_HORIZONTAL + G";
_lblwait.setGravity((int) (__c.Gravity.CENTER_HORIZONTAL+__c.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 179;BA.debugLine="lblWait.Text = \"Please wait...\"";
_lblwait.setText((Object)("Please wait..."));
 //BA.debugLineNum = 180;BA.debugLine="lblWait.TextColor = FileTextColor1";
_lblwait.setTextColor(_filetextcolor1);
 //BA.debugLineNum = 181;BA.debugLine="lblWait.TextSize = 18";
_lblwait.setTextSize((float) (18));
 //BA.debugLineNum = 182;BA.debugLine="lblWait.Typeface = Typeface.DEFAULT_BOLD";
_lblwait.setTypeface(__c.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 183;BA.debugLine="pnlFiles.AddView(lblWait, 20dip, pnlFiles.Heigh";
_pnlfiles.AddView((android.view.View)(_lblwait.getObject()),__c.DipToCurrent((int) (20)),(int) (_pnlfiles.getHeight()/(double)2-__c.DipToCurrent((int) (13))),(int) (_pnlfiles.getWidth()-__c.DipToCurrent((int) (40))),__c.DipToCurrent((int) (26)));
 //BA.debugLineNum = 184;BA.debugLine="DoEvents";
__c.DoEvents();
 };
 //BA.debugLineNum = 186;BA.debugLine="lstD.Initialize";
_lstd.Initialize();
 //BA.debugLineNum = 187;BA.debugLine="lstF.Initialize";
_lstf.Initialize();
 //BA.debugLineNum = 188;BA.debugLine="If Chemin <> \"/\" Then AddEntry(0, \"Listado de co";
if ((_chemin).equals("/") == false) { 
_addentry((int) (0),"Listado de configuracion","",__c.False);};
 //BA.debugLineNum = 189;BA.debugLine="For i = 0 To lst.Size - 1";
{
final int step24 = 1;
final int limit24 = (int) (_lst.getSize()-1);
for (_i = (int) (0) ; (step24 > 0 && _i <= limit24) || (step24 < 0 && _i >= limit24); _i = ((int)(0 + _i + step24)) ) {
 //BA.debugLineNum = 190;BA.debugLine="If File.IsDirectory(Chemin, lst.Get(i)) Then";
if (__c.File.IsDirectory(_chemin,BA.ObjectToString(_lst.Get(_i)))) { 
 }else if(__c.Not(_bonlyfolders)) { 
 //BA.debugLineNum = 193;BA.debugLine="If lstFiltre.Size = 0 Then";
if (_lstfiltre.getSize()==0) { 
 //BA.debugLineNum = 194;BA.debugLine="lstF.Add(lst.Get(i))";
_lstf.Add(_lst.Get(_i));
 }else {
 //BA.debugLineNum = 196;BA.debugLine="Dim NomFichier As String";
_nomfichier = "";
 //BA.debugLineNum = 197;BA.debugLine="NomFichier = lst.Get(i)";
_nomfichier = BA.ObjectToString(_lst.Get(_i));
 //BA.debugLineNum = 198;BA.debugLine="NomFichier = NomFichier.ToLowerCase";
_nomfichier = _nomfichier.toLowerCase();
 //BA.debugLineNum = 199;BA.debugLine="For f = 0 To lstFiltre.Size - 1";
{
final int step33 = 1;
final int limit33 = (int) (_lstfiltre.getSize()-1);
for (_f = (int) (0) ; (step33 > 0 && _f <= limit33) || (step33 < 0 && _f >= limit33); _f = ((int)(0 + _f + step33)) ) {
 //BA.debugLineNum = 200;BA.debugLine="If NomFichier.EndsWith(lstFiltre.Get(f)) The";
if (_nomfichier.endsWith(BA.ObjectToString(_lstfiltre.Get(_f)))) { 
 //BA.debugLineNum = 201;BA.debugLine="lstF.Add(lst.Get(i))";
_lstf.Add(_lst.Get(_i));
 //BA.debugLineNum = 202;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 };
 }
};
 //BA.debugLineNum = 208;BA.debugLine="lstD.SortCaseInsensitive(True)";
_lstd.SortCaseInsensitive(__c.True);
 //BA.debugLineNum = 209;BA.debugLine="For i = 0 To lstD.Size - 1";
{
final int step43 = 1;
final int limit43 = (int) (_lstd.getSize()-1);
for (_i = (int) (0) ; (step43 > 0 && _i <= limit43) || (step43 < 0 && _i >= limit43); _i = ((int)(0 + _i + step43)) ) {
 //BA.debugLineNum = 210;BA.debugLine="AddEntry(lstFichiers.NumberOfItems, \"/ \" & lstD";
_addentry(_lstfichiers._numberofitems(),"/ "+BA.ObjectToString(_lstd.Get(_i)),"",_bmultifolderselection);
 }
};
 //BA.debugLineNum = 212;BA.debugLine="lstF.SortCaseInsensitive(True)";
_lstf.SortCaseInsensitive(__c.True);
 //BA.debugLineNum = 213;BA.debugLine="For i = 0 To lstF.Size - 1";
{
final int step47 = 1;
final int limit47 = (int) (_lstf.getSize()-1);
for (_i = (int) (0) ; (step47 > 0 && _i <= limit47) || (step47 < 0 && _i >= limit47); _i = ((int)(0 + _i + step47)) ) {
 //BA.debugLineNum = 214;BA.debugLine="AddEntry(lstFichiers.NumberOfItems, lstF.Get(i)";
_addentry(_lstfichiers._numberofitems(),BA.ObjectToString(_lstf.Get(_i)),_displaysize(__c.File.Size(_chemin,BA.ObjectToString(_lstf.Get(_i)))),_bmultifileselection);
 }
};
 //BA.debugLineNum = 216;BA.debugLine="lstFichiers.ResizePanel";
_lstfichiers._resizepanel();
 //BA.debugLineNum = 217;BA.debugLine="strChemin = Chemin";
_strchemin = _chemin;
 //BA.debugLineNum = 218;BA.debugLine="lblWait.RemoveView";
_lblwait.RemoveView();
 }else {
 //BA.debugLineNum = 221;BA.debugLine="ToastMessageShow(\"Unable to access folder\", Fals";
__c.ToastMessageShow("Unable to access folder",__c.False);
 //BA.debugLineNum = 222;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public boolean  _sp_touch(Object _viewtag,int _action,float _x,float _y,Object _motionevent) throws Exception{
 //BA.debugLineNum = 1137;BA.debugLine="Private Sub SP_Touch(ViewTag As Object, Action As";
 //BA.debugLineNum = 1138;BA.debugLine="If Action = 0 Then";
if (_action==0) { 
 //BA.debugLineNum = 1139;BA.debugLine="If Y < pnlDisplay.Top Or Y > pnlDisplay.Top + pn";
if (_y<_pnldisplay.getTop() || _y>_pnldisplay.getTop()+_pnldisplay.getHeight()) { 
 //BA.debugLineNum = 1141;BA.debugLine="bIgnoreEvent = True";
_bignoreevent = __c.True;
 }else {
 //BA.debugLineNum = 1143;BA.debugLine="bIgnoreEvent = False";
_bignoreevent = __c.False;
 };
 };
 //BA.debugLineNum = 1146;BA.debugLine="If bIgnoreEvent Then Return False";
if (_bignoreevent) { 
if (true) return __c.False;};
 //BA.debugLineNum = 1148;BA.debugLine="Select Case Action";
switch (_action) {
case 0: 
case 2: {
 //BA.debugLineNum = 1150;BA.debugLine="bUserMovingPnl = True";
_busermovingpnl = __c.True;
 //BA.debugLineNum = 1151;BA.debugLine="TimeOut.Enabled = False";
_timeout.setEnabled(__c.False);
 //BA.debugLineNum = 1152;BA.debugLine="Anim.Stop(pnlDisplay)";
_anim.Stop((android.view.View)(_pnldisplay.getObject()));
 //BA.debugLineNum = 1153;BA.debugLine="pnlDisplay.Top = Min(Max(0, Y * (1 - (pnlDispla";
_pnldisplay.setTop((int) (__c.Min(__c.Max(0,_y*(1-(_pnldisplay.getHeight()/(double)_pnlrange.getHeight()))),_maxpos)));
 //BA.debugLineNum = 1154;BA.debugLine="svFichiers.VerticalScrollPosition = pnlDisplay.";
_svfichiers.setVerticalScrollPosition((int) (_pnldisplay.getTop()/(double)_maxpos*(_svfichiers.getPanel().getHeight()-_svfichiers.getHeight())));
 break; }
default: {
 //BA.debugLineNum = 1156;BA.debugLine="bUserMovingPnl = False";
_busermovingpnl = __c.False;
 //BA.debugLineNum = 1157;BA.debugLine="TimeOut.Interval = Duration";
_timeout.setInterval((long) (_duration));
 //BA.debugLineNum = 1158;BA.debugLine="TimeOut.Enabled = True";
_timeout.setEnabled(__c.True);
 break; }
}
;
 //BA.debugLineNum = 1160;BA.debugLine="Return True";
if (true) return __c.True;
 //BA.debugLineNum = 1161;BA.debugLine="End Sub";
return false;
}
public String  _svf_scrollchanged(int _posx,int _posy) throws Exception{
 //BA.debugLineNum = 1107;BA.debugLine="Private Sub SVF_ScrollChanged(PosX As Int, PosY As";
 //BA.debugLineNum = 1108;BA.debugLine="If Not(FastScrollEnabled) Then Return";
if (__c.Not(_fastscrollenabled)) { 
if (true) return "";};
 //BA.debugLineNum = 1110;BA.debugLine="If bWaitForScroll Then";
if (_bwaitforscroll) { 
 //BA.debugLineNum = 1112;BA.debugLine="If pnlDisplay.Top = CalcNewTop Then";
if (_pnldisplay.getTop()==_calcnewtop()) { 
 //BA.debugLineNum = 1113;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 1115;BA.debugLine="bWaitForScroll = False";
_bwaitforscroll = __c.False;
 };
 };
 //BA.debugLineNum = 1119;BA.debugLine="If svFichiers.Panel.Height > svFichiers.Height Th";
if (_svfichiers.getPanel().getHeight()>_svfichiers.getHeight()) { 
 //BA.debugLineNum = 1121;BA.debugLine="pnlRange.Visible = True";
_pnlrange.setVisible(__c.True);
 };
 //BA.debugLineNum = 1124;BA.debugLine="If Not(bUserMovingPnl) Then";
if (__c.Not(_busermovingpnl)) { 
 //BA.debugLineNum = 1125;BA.debugLine="TimeOut.Enabled = False";
_timeout.setEnabled(__c.False);
 //BA.debugLineNum = 1126;BA.debugLine="Anim.Stop(pnlDisplay)";
_anim.Stop((android.view.View)(_pnldisplay.getObject()));
 //BA.debugLineNum = 1127;BA.debugLine="pnlDisplay.Top = CalcNewTop";
_pnldisplay.setTop(_calcnewtop());
 //BA.debugLineNum = 1128;BA.debugLine="If PosY = svFichiers.VerticalScrollPosition Then";
if (_posy==_svfichiers.getVerticalScrollPosition()) { 
 //BA.debugLineNum = 1130;BA.debugLine="TimeOut.Interval = Duration";
_timeout.setInterval((long) (_duration));
 //BA.debugLineNum = 1131;BA.debugLine="TimeOut.Enabled = True";
_timeout.setEnabled(__c.True);
 //BA.debugLineNum = 1132;BA.debugLine="bWaitForScroll = True";
_bwaitforscroll = __c.True;
 };
 };
 //BA.debugLineNum = 1135;BA.debugLine="End Sub";
return "";
}
public String  _timeout_tick() throws Exception{
 //BA.debugLineNum = 1098;BA.debugLine="Private Sub TimeOut_Tick";
 //BA.debugLineNum = 1099;BA.debugLine="TimeOut.Enabled = False";
_timeout.setEnabled(__c.False);
 //BA.debugLineNum = 1100;BA.debugLine="Anim.Start(pnlDisplay)";
_anim.Start((android.view.View)(_pnldisplay.getObject()));
 //BA.debugLineNum = 1101;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
