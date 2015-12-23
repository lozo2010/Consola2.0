Type=Activity
Version=5.5
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: true
	#IncludeTitle: false
#End Region



'11-1-2015 -> AÑADIMOS LA FUNCION DE ELEGIR LA CONFIGURACION. (PRUEBA, PRIMERA HORA, VIENTO ALTO, VIENTO MEDIO, CALMA, TERMICA)





Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

Dim flag As Int=0
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private btOK As Button
	Private Button1 As Button
	Private etFichero As EditText
	Private etGiro As EditText
	Private etTiempo As EditText
	Private lvDatos As ListView
	Private spconfig As Spinner
	Private spServo As Spinner
	
	
	
	
	Private dial As InputDialog

	Private spTiempo As Spinner
	Private Label8 As Label
	Private Label9 As Label
	Dim NOMBRE_FICHERO As String
	Dim config As String
	
	Dim di As CustomDialog2
	
	
	
	
	Dim lista1 As List
	Dim contador_lineas As Int
	Dim servo As String
	Dim giro As String
	Dim tiempo As String
	Dim tipo_tiempo As String
	
	Dim servo_lv As String
	Dim tiempo_lv As String
	Dim tipo_tiempo_lv As String
	Dim giro_lv As String
	
	
	Dim tiempo_aux As String
	Dim tipo_tiempo_aux As String
	Dim giro_aux As String
	Dim servo_aux As String
	
	
	Private Label4 As Label
	Private Label2 As Label
	Private lbNumeroServo As Label
	Private lbModelo As Label
	Private Label10 As Label
	Dim posicion_mod As String


	Private Button2 As Button
	
	
	Dim valor_inicial As Int
	Dim valor_final As Int
	Dim numero_suma As Int
	Private ListView1 As ListView
	Dim BC As ColorDrawable
	Dim BC1 As ColorDrawable
	Private Panel1 As Panel
	Private Panel2 As Panel
	Private Panel3 As Panel
	Private Panel4 As Panel
	Dim spconfig1 As Spinner
	Private btComenzar As Button
	Private btSalir As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	
	Activity.LoadLayout("f1c1")
	Button1.Visible=False
	Button2.Visible=False
	btOK.Visible=False
'OJO ESTA SUBRUTINA BORRA TODO DIRROOTEXTERNAL	

'borrar
'------------------------------------------------
BC1.Initialize(Colors.White,5)

InitPanel(Panel1,3,Colors.White,Colors.Black)
InitPanel(Panel2,3,Colors.White,Colors.Black)
InitPanel(Panel3,3,Colors.White,Colors.Black)
InitPanel(Panel4,3,Colors.White,Colors.Black)
Label10.Text="F1-C"

BC.Initialize(Colors.Blue,5)


spServo.Add("")
spServo.Add("SERVO 1")
spServo.Add("SERVO 2")
spServo.Add("SERVO 3")
spServo.Add("SERVO 4")
spServo.Add("SERVO 5")
'spServo.DropdownBackgroundColor=Colors.Red
'spServo.Background=BC
	spTiempo.Add("PULSE AQUI")
	spTiempo.Add("DECIMAS")
	spTiempo.Add("SEGUNDOS")
	spTiempo.Add("MINUTOS")
	
	spconfig.Add("PULSE AQUI")
	spconfig.Add("PRUEBA")
	spconfig.Add("PRIMERA HORA")
	spconfig.Add("VIENTO ALTO")
	spconfig.Add("VIENTO MEDIO")
	spconfig.Add("CALMA")
	spconfig.Add("TERMICA")
	
	spServo.DropdownBackgroundColor=Colors.White
	spTiempo.DropdownBackgroundColor=Colors.White
	spconfig.DropdownBackgroundColor=Colors.White
	
	spServo.Visible=False
	spTiempo.Visible=False
	Label2.Visible=False
	Panel3.Visible=False
	
	Label4.Visible=False
	Panel4.Visible=False
	etTiempo.Visible=False
	Label8.Visible=True
	lbNumeroServo.Text="1"
	servo=lbNumeroServo.Text
	contador_lineas=1
			lista1.Initialize
elegir_modelo
	


End Sub

Sub Activity_Resume
flag=1
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub elegir_modelo
Dim valor As Int
dial.Input=""
valor=	dial.Show("","NOMBRE MODELO","Aceptar","","",Null)
		ToastMessageShow(valor,False)

		
	If dial.Response=-1 Then
		ToastMessageShow("ok",False)
		
	
	End If 

End Sub




Sub spServo_ItemClick (Position As Int, Value As Object)
	
	servo=Position
	lbNumeroServo.Text=servo
	
	
End Sub




Sub spconfig_ItemClick (Position As Int, Value As Object)
	
	If Value="PULSE AQUI" Then
	
		btOK.Visible=False
	Else 
	
	config=Value
	etFichero.Text = dial.Input & " " '& config
	etFichero_EnterPressed
	End If
	

End Sub


Sub lvDatos_ItemClick (Position As Int, Value As Object)
Button2.Enabled=True
Button2.Visible=True
Button1.Enabled=False
Button1.Visible=False
btOK.Enabled=False
btOK.Visible=False
Dim str As String

	If Position<=4 Then
	spServo.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label8.Visible=True
	
	
	
		posicion_mod=Position
		str=Value
		servo_lv=str.SubString2(6,7)
		giro_lv=str.SubString2(16,19)
		lbNumeroServo.text=servo_lv
			If giro_lv<=9 Then
		etGiro.Text=giro_lv.SubString(2)
		End If
		
If giro_lv <=99 Then
	If giro_lv>9 Then
		etGiro.Text=giro_lv.SubString2(1,3)

	End If
End If

If giro_lv >99 Then

		etGiro.Text=giro_lv

End If
		
		
		
		
		ToastMessageShow("giro "&giro_lv,True)
		ToastMessageShow("servo "&servo_lv,True)

	End If
	
	If Position>=5 Then
		If Position<=9 Then
			spServo.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label9.Visible=True
		
		
			posicion_mod=Position
			str=Value
			servo_lv=str.SubString2(6,7)
		giro_lv=str.SubString2(16,19)
		
		
				If giro_lv<=9 Then
		etGiro.Text=giro_lv.SubString(2)
		End If
		
If giro_lv <=99 Then
	If giro_lv>9 Then
		etGiro.Text=giro_lv.SubString2(1,3)

	End If
End If

If giro_lv >99 Then

		etGiro.Text=giro_lv

End If
		
		
		ToastMessageShow("giro "&giro_lv,True)
		ToastMessageShow("servo "&servo_lv,True)
		End If
	End If
	If Position>=10 Then
		spServo.Visible=True
		spTiempo.Visible=True
		Label2.Visible=True
		Panel3.Visible=True
		Label4.Visible=True
		Panel4.Visible=True
		etTiempo.Visible=True
		Label9.Visible=False
		Label8.Visible=False 
		lbNumeroServo.Text=""

	
		posicion_mod=Position
		str=Value
		ToastMessageShow(str,True)
		ToastMessageShow(str.SubString2(30,34),True)
		ToastMessageShow(str.SubString2(49,50),True)
		
		tiempo_lv =str.SubString2(30,34)
		etTiempo.Text=tiempo_lv
		
				If tiempo_lv<=9 Then
		etTiempo.Text=tiempo_lv.SubString(2)
		End If
		
If tiempo_lv <=99 Then
	If tiempo_lv>9 Then
		etTiempo.Text=tiempo_lv.SubString2(1,3)

	End If
End If

If tiempo_lv >99 Then

		etTiempo.Text=tiempo_lv

End If
		
		
		servo_lv=str.SubString2(6,7)
		lbNumeroServo.Text=servo_lv
		spServo.SelectedIndex=servo_lv
	'	spServo.SelectedItem="Servo "&servo_lv
		
		giro_lv=str.SubString2(16,19)
		If giro_lv<=9 Then
		etGiro.Text=giro_lv.SubString(2)
		End If
		
If giro_lv <=99 Then
	If giro_lv>9 Then
		etGiro.Text=giro_lv.SubString2(1,3)

	End If
End If

If giro_lv >99 Then

		etGiro.Text=giro_lv

End If
		
		tipo_tiempo_aux=str.SubString2(49,50)
		If tipo_tiempo_aux="D" Then
			tipo_tiempo="DECIMAS"
		End If
		
		If tipo_tiempo_aux="S" Then
			tipo_tiempo="SEGUNDOS"
		End If
		
		If tipo_tiempo_aux="M" Then
			tipo_tiempo="MINUTOS"
			
		End If

	End If
	ToastMessageShow(Value,True)
	

End Sub



Sub Button2_Click

If spServo.SelectedItem="PULSE AQUI" Then
ToastMessageShow("VALOR NO VALIDO",True)
End If
Try



Dim aux_valor_tiempo As String
Dim aux_valor_giro As String
Dim pos_valor As Int
Dim pos_mod_aux As String
Dim aux_valor_giro_inicio As String
If posicion_mod<5 Then

		If giro_lv<=9 Then
			aux_valor_giro_inicio="00"&giro
			lista1.set(posicion_mod,aux_valor_giro_inicio)
		End If

		If giro <=99 Then
			If giro>9 Then
				aux_valor_giro="0"&giro
			lista1.set(posicion_mod,aux_valor_giro_inicio)
			End If
		End If

		If giro >99 Then
			lista1.set(posicion_mod,giro)
		End If

	spServo.Visible=True
		spTiempo.Visible=True
		Label2.Visible=True
		Panel3.Visible=True
		Label4.Visible=True
	Panel4.Visible=True
		etTiempo.Visible=True
		Label8.Visible=False
End If





If posicion_mod<20 Then
	If posicion_mod>=10 Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+10
	ToastMessageShow("Linea: "&posicion_mod&" Unidad: "&pos_mod_aux&" Valor en lista: "&pos_valor,True)
	
		If tiempo<=9 Then
			aux_valor_tiempo="00"&tiempo
			lista1.Set(pos_valor,aux_valor_tiempo)
		End If

		If tiempo <=99 Then
			If tiempo>9 Then
				aux_valor_tiempo="0"&tiempo

			lista1.Set(pos_valor,aux_valor_tiempo)
			End If
		End If

		If tiempo >99 Then
			

			lista1.Set(pos_valor,tiempo)
		End If
	
'------------------------------------------------------------	
		If tipo_tiempo="DECIMAS" Then
			lista1.set(pos_valor+1,"0")
		End If
		If tipo_tiempo="SEGUNDOS" Then
			lista1.set(pos_valor+1,"1")
		End If
		If tipo_tiempo="MINUTOS" Then
			lista1.set(pos_valor+1,"0")
		End If
'---------------------------------------	
	lista1.set(pos_valor+2,servo)
	
'-----------------------------------------
		If giro<=9 Then
			aux_valor_giro="00"&giro
			lista1.set(pos_valor+3,aux_valor_giro)
		End If

		If giro <=99 Then
			If giro>9 Then
				aux_valor_giro="0"&giro
			lista1.set(pos_valor+3,aux_valor_giro)
			End If
		End If

		If giro >99 Then
			lista1.set(pos_valor+3,giro)
		End If
	
	End If
End If

mod_datos(20,30,50)

mod_datos(30,40,90)

mod_datos(40,50,130)

mod_datos(50,60,170)







	File.WriteList(File.DirRootExternal,"Modelo_F1C_"&NOMBRE_FICHERO,lista1)
	lvDatos.Clear
	
	
	
	
				For i=0 To 4
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("Servo "&(i+1)&"   Giro: "&lista1.Get(i)&"  INICIO")
			Next
			
			For i=5 To 9 
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("Servo "&(i-4)&"   Giro: "&lista1.Get(i)&"  SOS")
			Next
			
			For i=10 To lista1.Size-1 Step 4
				tiempo_aux=lista1.Get(i) 
				tipo_tiempo_aux=lista1.Get(i+1)
				servo_aux=lista1.Get(i+2)
				giro_aux=lista1.Get(i+3)
				If tipo_tiempo_aux="0" Then
				tipo_tiempo_aux="DECIMAS"
				End If
'				
				If tipo_tiempo_aux="1" Then
				tipo_tiempo_aux="SEGUNDOS"
				End If
				
				If tipo_tiempo_aux="2" Then
				tipo_tiempo_aux="MINUTOS"
				End If
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("Servo "&servo_aux&"   Giro: "&giro_aux&"    Tiempo: "&tiempo_aux&"  Tipo tiempo: "&tipo_tiempo)
'			
			Next

	Button2.Enabled=False
Button2.Visible=False
Button1.Enabled=True
Button1.Visible=True


lbNumeroServo.Text=""
spServo.SelectedIndex=0
etGiro.Text=""
etTiempo.Text=""
spTiempo.SelectedIndex=0
spServo.Background=BC1
Catch

End Try
End Sub

Sub lvDatos_ItemLongClick (Position As Int, Value As Object)
	
End Sub


Sub etTiempo_TextChanged (Old As String, New As String)
	tiempo=New
End Sub


Sub etGiro_TextChanged (Old As String, New As String)
	giro=New
End Sub


Sub etFichero_EnterPressed
	If etFichero.Text="" Then
	ToastMessageShow("error",True)
	
	Else
	
	
	NOMBRE_FICHERO=etFichero.Text & " " & config
	
		If File.Exists(File.DirRootExternal,"Modelo_F1C_"&NOMBRE_FICHERO)=True Then
	
	
			lbModelo.Text=NOMBRE_FICHERO
	lista1=File.ReadList(File.DirRootExternal,"Modelo_F1C_"&NOMBRE_FICHERO)
			For i=0 To 4
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("Servo "&(i+1)&"   Giro: "&lista1.Get(i)&"  INICIO")
			Next
			
			For i=5 To 9 
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("Servo "&(i-4)&"   Giro: "&lista1.Get(i)&"  SOS")
			Next
			
			For i=10 To lista1.Size-1 Step 4
				tiempo_aux=lista1.Get(i) 
				tipo_tiempo_aux=lista1.Get(i+1)
				servo_aux=lista1.Get(i+2)
				giro_aux=lista1.Get(i+3)
				If tipo_tiempo_aux="0" Then
				tipo_tiempo_aux="DECIMAS"
				End If
				
				If tipo_tiempo_aux="1" Then
				tipo_tiempo_aux="SEGUNDOS"
				End If
				
				If tipo_tiempo_aux="2" Then
				tipo_tiempo_aux="MINUTOS"
				End If
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("Servo "&servo_aux&"   Giro: "&giro_aux&"    Tiempo: "&tiempo_aux&"  Tipo tiempo: "&tipo_tiempo_aux)
			
			Next
		spServo.Visible=True
		spTiempo.Visible=True
		Label2.Visible=True
		Panel3.Visible=True
		Label4.Visible=True
		Panel4.Visible=True
		etTiempo.Visible=True
		Label9.Visible=False
		Label8.Visible=False 
		lbNumeroServo.Text=""
		btOK.Enabled=False
		btOK.Visible=False
		Button1.Enabled=True
		Button1.Visible=True
		contador_lineas=12
	
		Else
			ToastMessageShow("no existe",False)
			'	NOMBRE_FICHERO=etFichero.Text & " " & config
			lbModelo.Text=NOMBRE_FICHERO
lvDatos.Clear
		spServo.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label9.Visible=False
		Label8.Visible=True 
	'	lbNumeroServo.Text=""
		btOK.Enabled=True
		btOK.Visible=False
		Button1.Enabled=False
		Button1.Visible=False
		Button2.Visible=False
		Button2.Enabled=False
				btOK.Visible=True

			File.WriteList(File.DirRootExternal,"Modelo_F1C_"&NOMBRE_FICHERO,lista1)
	contador_lineas=1
		End If
	
	End If
End Sub


Sub Button1_Click


Try
Dim aux_valor_tiempo As String
Dim aux_valor_giro As String
If contador_lineas<254 Then
If contador_lineas>=12 Then


If tiempo<=9 Then
	aux_valor_tiempo="00"&tiempo
	lista1.Add(aux_valor_tiempo)

End If

If tiempo <=99 Then
	If tiempo>9 Then
	aux_valor_tiempo="0"&tiempo
	lista1.Add(aux_valor_tiempo)

	End If
End If

If tiempo >99 Then

	aux_valor_tiempo=tiempo
	lista1.Add(aux_valor_tiempo)
End If




If tipo_tiempo="DECIMAS" Then

lista1.Add("0")
End If
If tipo_tiempo="SEGUNDOS" Then

lista1.Add("1")
End If
If tipo_tiempo="MINUTOS" Then

lista1.Add("0")
End If

lista1.Add(servo)
If giro<=9 Then
	aux_valor_giro="00"&giro
	lista1.Add(aux_valor_giro)

End If

If giro <=99 Then
	If giro>9 Then
	aux_valor_giro="0"&giro
	lista1.Add(aux_valor_giro)

	End If
End If

If giro >99 Then
	aux_valor_giro=giro

lista1.Add(aux_valor_giro)

End If

log_lista
contador_lineas=contador_lineas+4
Log("numero lineas  "&(contador_lineas-2)) 



End If


Else
ToastMessageShow("maximo lineas",True)
Log("numero lineas  "&(contador_lineas-2))
Log("------------------------------")
		Log("------------------------------")

	For i=0 To lista1.Size- 1

		Log(lista1.Get(i))
		
	Next
End If
    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black

	lvDatos.AddSingleLine("Servo "&servo&"   Giro: "&aux_valor_giro&"    Tiempo: "&aux_valor_tiempo&"  Tipo tiempo: "&tipo_tiempo)
	File.WriteList(File.DirRootExternal,"Modelo_F1C_"&NOMBRE_FICHERO,lista1)
ToastMessageShow("Servo "&servo&"   Giro: "&aux_valor_giro&"    Tiempo: "&aux_valor_tiempo&"  Tipo tiempo: "&tipo_tiempo,True)
ToastMessageShow("Servo "&servo&"   Giro: "&aux_valor_giro&"    Tiempo: "&aux_valor_tiempo&"  Tipo tiempo: "&tipo_tiempo,True)

lbNumeroServo.Text=""
spServo.SelectedIndex=0
etGiro.Text=""
etTiempo.Text=""
spTiempo.SelectedIndex=0
spServo.Background=BC1
Catch

End Try
End Sub


Sub btOK_Click
Try
Dim aux_valor_giro As String

If contador_lineas<=5 Then

	servo=lbNumeroServo.Text
If giro<=9 Then
	aux_valor_giro="00"&giro
	lista1.Add(aux_valor_giro)

End If

If giro <=99 Then
	If giro>9 Then
	aux_valor_giro="0"&giro
	lista1.Add(aux_valor_giro)

	End If
End If

If giro >99 Then

lista1.Add(giro)

End If'	lista1.Add(servo)
	If contador_lineas=5 Then
		Label8.Visible=False
		Label9.Visible=True
		lbNumeroServo.Text="1"
			contador_lineas=contador_lineas+1
	Else
	contador_lineas=contador_lineas+1
	lbNumeroServo.Text=contador_lineas
	End If
	
	
End If



If contador_lineas>=6 Then
	If contador_lineas<=11 Then
	servo=lbNumeroServo.Text

	If contador_lineas>=7 Then
		servo=lbNumeroServo.Text
If giro<=9 Then
	aux_valor_giro="00"&giro
	lista1.Add(aux_valor_giro)

End If

If giro <=99 Then
	If giro>9 Then
	aux_valor_giro="0"&giro
	lista1.Add(aux_valor_giro)

	End If
End If

If giro >99 Then

lista1.Add(giro)

End If	'	lista1.Add(servo)
	End If
	
	If contador_lineas=11 Then
		spServo.Visible=True
		spTiempo.Visible=True
		Label2.Visible=True
		Panel3.Visible=True
		Label4.Visible=True
		Panel4.Visible=True
		etTiempo.Visible=True
		Label9.Visible=False
		lbNumeroServo.Text=""
	For i=0 To lista1.Size- 1

		Log(lista1.Get(i))
		'Log("------------------------------")
		'Log("------------------------------")
	Next
		contador_lineas=contador_lineas+1
		btOK.Enabled=False
		btOK.Visible=False
		Button1.Enabled=True
		Button1.Visible=True
		datos_lv

	Else
		contador_lineas=contador_lineas+1
		lbNumeroServo.Text=contador_lineas-6
	
	
	End If
	
End If	
	
End If

Log("numero lineas  "&contador_lineas) 

	File.WriteList(File.DirRootExternal,"Modelo_F1C_"&NOMBRE_FICHERO,lista1)

etGiro.Text=""
Catch 

End Try
End Sub


Sub spTiempo_ItemClick (Position As Int, Value As Object)

If Value="PULSE AQUI" Then
	ToastMessageShow("VALOR NO VALIDO",True)
Else
	tipo_tiempo=Value

End If
End Sub



Sub log_lista
	For i=0 To lista1.Size- 1

Log(lista1.Get(i))

Next


End Sub

Sub datos_lv

For i=0 To 4
    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
lvDatos.AddSingleLine("Servo "&(i+1)&"   Giro: "&lista1.Get(i)&"  INICIO")

Next
For i=5 To 9
    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black

lvDatos.AddSingleLine("Servo "&(i-4)&"   Giro: "&lista1.Get(i)&"  SOS")

Next



End Sub


Sub lbModelo_LongClick
	elegir_modelo
	
	
End Sub



Sub mod_datos (valor_ini As Int, valor_fin As Int, valor_sum As Int)
Dim aux_valor_tiempo As String
Dim aux_valor_giro As String
Dim pos_valor As Int
Dim pos_mod_aux As String
Dim aux_valor_giro_inicio As String



If posicion_mod<valor_fin Then
	If posicion_mod>=valor_ini Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+valor_sum
	ToastMessageShow("Linea: "&posicion_mod&" Unidad: "&pos_mod_aux&" Valor en lista: "&pos_valor,True)
	
		If tiempo<=9 Then
			aux_valor_tiempo="00"&tiempo
			lista1.Set(pos_valor,aux_valor_tiempo)
		End If

		If tiempo <=99 Then
			If tiempo>9 Then
				aux_valor_tiempo="0"&tiempo

			lista1.Set(pos_valor,aux_valor_tiempo)
			End If
		End If

		If tiempo >99 Then
			

			lista1.Set(pos_valor,tiempo)
		End If
	
'------------------------------------------------------------	
		If tipo_tiempo="DECIMAS" Then
			lista1.set(pos_valor+1,"0")
		End If
		If tipo_tiempo="SEGUNDOS" Then
			lista1.set(pos_valor+1,"1")
		End If
		If tipo_tiempo="MINUTOS" Then
			lista1.set(pos_valor+1,"0")
		End If
'---------------------------------------	
	lista1.set(pos_valor+2,servo)
	
'-----------------------------------------
		If giro<=9 Then
			aux_valor_giro="00"&giro
			lista1.set(pos_valor+3,aux_valor_giro)
		End If

		If giro <=99 Then
			If giro>9 Then
				aux_valor_giro="0"&giro
			lista1.set(pos_valor+3,aux_valor_giro)
			End If
		End If

		If giro >99 Then
			lista1.set(pos_valor+3,giro)
		End If
	
	End If
End If




End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
Dim lv As CustomListView
	ListView1.SetSelection(Position)
	lvDatos.SetSelection(Position)

End Sub



Sub InitPanel(pnl As Panel,BorderWidth As Float, FillColor As Int, BorderColor As Int)
	Dim Rec As Rect
	Dim Canvas1 As Canvas
	Dim BorderWidth_2 As Float
	
	BorderWidth_2=BorderWidth/2
	Rec.Initialize(BorderWidth_2,BorderWidth_2,pnl.Width-BorderWidth_2,pnl.Height-BorderWidth_2)
	Canvas1.Initialize(pnl)
	Canvas1.DrawRect(Rec,FillColor,True,FillColor)
	Canvas1.DrawRect(Rec,BorderColor,False,BorderWidth)
End Sub


Sub borrar 
Dim FilesToDelete As List

FilesToDelete.Initialize
FilesToDelete.AddAll(File.ListFiles(File.DirRootExternal))

For I = 0 To FilesToDelete.Size -1
    
    File.Delete(File.DirRootExternal, FilesToDelete.Get(I))

Next

End Sub
Sub btSalir_LongClick
	
'	flag=1
'	Log("flag F1C: "&flag)
'Activity.Finish
'	StartActivity(Main)
ExitApplication
End Sub


Sub btComenzar_LongClick
'flag=2
'	Log("flag F1C: "&flag)

	Activity.Finish
	StartActivity(Main)
End Sub