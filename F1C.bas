Type=Activity
Version=5.5
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: true
	#IncludeTitle: false
#End Region



Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
Dim astreams1 As AsyncStreams

Dim flag As Int=0
Dim strModelo As String
Dim usb1 As UsbSerial
Dim strspinner As String
Dim strfichero As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim dlgFileExpl As ClsExplorer

	Private btOK As Button
	Private Button1 As Button
	Private etFichero As EditText
	Private etGiro As EditText
	Private etTiempo As EditText
	Private lvDatos As ListView
	Private spconfig As Spinner
	Private spServo As Spinner
	
	Dim timer1 As Timer
	Dim ex As Boolean
	
	Private dial As InputDialog

	Private spTiempo As Spinner
	Private Label8 As Label
	Private Label9 As Label
	Dim NOMBRE_FICHERO As String
	Dim config As String
	
	Dim di As CustomDialog2
	
	
	
			Dim contador As Int
	Dim buffer_tx(512) As Byte
	
	Dim valor_d As Byte
	Dim first_time As Boolean
	Private btEscribirMemoria As Button
	Private lbrx As Label
	Private bt124 As Button
	Private bt1 As Button
	Private btascii49 As Button
	Dim flag_d As Boolean
	Private btTestAvr As Button
	Dim x As Int
	Dim flag_rx As Boolean
	Dim contador As Int

	Private btLeer As Button
	
	Dim listaconfiguracion As List
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
	Dim directorio As String
	
	Dim tiempo_aux As String
	Dim tipo_tiempo_aux As String
		Dim tipo_tiempo_aux1 As String

	Dim giro_aux As String
	Dim servo_aux As String
		Dim servo_aux_nombre As String
	
	Private Label4 As Label
	Private Label2 As Label
	Private lbNumeroServo As Label
	Private lbModelo As Label
	Private label10 As Label
	Dim posicion_mod As String

	Dim tiempo_ante_lv As String
	Dim tiempo_post_lv As String
	Dim servo_ante_lv As String
	Dim servo_post_lv As String
	Dim TIPO_ANTE_LV As String
	Dim tIPO_POST_LV As String
	Dim flag_multi As Boolean
	
	
	
	Dim flag_button2 As Boolean=False
	
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
	
	Dim ime1 As IME
	Private btgrabar As Button
	Dim tiempo_minimo As Int
	Dim tiempo_actual As Int
	Dim tiempo_repite As Boolean
	Private Label5 As Label
		Dim tim As Timer
	Private lbservotxt As Label
		Private btLeer As Button
	Private cbled As CheckBox
	
		Dim nombre_leido As String
	
	
	Dim flag_lectura As Boolean=False
	Dim t As Int=0
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Try
	Activity.LoadLayout("f1c1")
	Button1.Visible=False
	Button2.Visible=False
	btOK.Visible=False
		timer1.Initialize("Timer1",1000)
	timer1.Enabled=True
	'	If FirstTime Then
	tim.Initialize("tim",500)
	tim.Enabled=True
		ime1.SetCustomFilter(etTiempo,etTiempo.INPUT_TYPE_DECIMAL_NUMBERS,"0123456789")
	ime1.SetCustomFilter(etGiro,etGiro.INPUT_TYPE_DECIMAL_NUMBERS,"0123456789")
	'End If
'OJO ESTA SUBRUTINA BORRA TODO DirRootExternal & "F1_B" & "F1_B	

'borrar
'------------------------------------------------
directorio=File.DirRootExternal & "/Consola"
File.MakeDir(File.DirRootExternal & "/Consola","F1C")
File.MakeDir(File.DirRootExternal & "/Consola/F1C","configuracion")
BC1.Initialize(Colors.White,5)
ime1.Initialize("")
InitPanel(Panel1,3,Colors.White,Colors.Black)
InitPanel(Panel2,3,Colors.White,Colors.Black)
InitPanel(Panel3,3,Colors.White,Colors.Black)
InitPanel(Panel4,3,Colors.White,Colors.Black)
label10.Text="F1-C"

BC.Initialize(Colors.Blue,5)


spServo.Add("PULSE AQUI")
spServo.Add("C.I.ALA")
spServo.Add("MOTOR")
spServo.Add("STABILO")
spServo.Add("TIMON")
spServo.Add("FLAP|FOLDER")
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
	spconfig.TextSize=23
	
	spServo.DropdownBackgroundColor=Colors.White
	spTiempo.DropdownBackgroundColor=Colors.White
	spconfig.DropdownBackgroundColor=Colors.White
	
	spServo.Visible=False
	spTiempo.Visible=False
	Label2.Visible=False
	Panel3.Visible=False
	Panel2.Visible=False
	Label8.Visible=False
	Label5.Visible=False
	lbNumeroServo.Visible=False
	lbservotxt.Visible=False
	
	
	Label4.Visible=False
	Panel4.Visible=False
	etTiempo.Visible=False
	Label8.Visible=False
	lbNumeroServo.Text="C.I.ALA"
	lbservotxt.Text="C.I.ALA"
	servo=lbNumeroServo.Text
	contador_lineas=1
			lista1.Initialize
			listaconfiguracion.Initialize2(Array As String("0","nombre"))
			listaconfiguracion.Set(1,NOMBRE_FICHERO)
				File.WriteList(File.DirRootExternal & "/Consola/F1C/configuracion","Modelo_F1C_"&NOMBRE_FICHERO,listaconfiguracion)

'elegir_modelo
etGiro.Enabled=False
spconfig.Enabled=False
lbModelo.Text="PULSE AQUI"
openusb
Catch

End Try
End Sub

Sub ficheronuevo
Try
ime1.HideKeyboard
spconfig.Enabled=True
If strspinner="PULSE AQUI" Then

spconfig.SelectedIndex=0

End If
lvDatos.CLEAR
lbModelo.Text=strfichero
Panel1.Visible=False
		spServo.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label9.Visible=False
		Label8.Visible=False 
			Panel2.Visible=False
	Label5.Visible=False
	lbNumeroServo.Visible=False
	lbservotxt.Visible=False
	tiempo_minimo=0
		lbNumeroServo.Text="C.I.ALA"
		lbservotxt.Text="C.I.ALA"
		btOK.Enabled=False
		btOK.Visible=False
		Button1.Enabled=False
		Button1.Visible=False
		Button2.Visible=False
		Button2.Enabled=False
		etGiro.Enabled=False
				btOK.Visible=False
		'etGiro.Enabled=True
		tiempo_actual=0
Catch

End Try

End Sub

Sub AbrirFichero
Dim numpos As Int
Try 

	'If etFichero.Text="" Then
	'ToastMessageShow("error",True)
	
	'Else
	Log("abro fichero")
NOMBRE_FICHERO=strfichero
 etFichero.Text=NOMBRE_FICHERO
numpos=NOMBRE_FICHERO.IndexOf(" ")
ToastMessageShow(NOMBRE_FICHERO,True)





ToastMessageShow(numpos,True)
	If strspinner="PRUEBA" Then
		spconfig.SelectedIndex=1
	End If 
	If strspinner="PRIMERA HORA" Then
		spconfig.SelectedIndex=2
	End If 
	If strspinner="VIENTO ALTO" Then
		spconfig.SelectedIndex=3
	End If 
		If strspinner="VIENTO MEDIO" Then
		spconfig.SelectedIndex=4
	End If 
	If strspinner="CALMA" Then
		spconfig.SelectedIndex=5
	End If 
	If strspinner="TERMICA" Then
		spconfig.SelectedIndex=6
	End If 
	
	
		
		If File.Exists(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO)=True Then
	
	
			lbModelo.Text=NOMBRE_FICHERO
			strModelo="Modelo_F1C_"&NOMBRE_FICHERO
	lista1=File.ReadList(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO)
	listaconfiguracion=File.ReadList(File.DirRootExternal & "/Consola/F1C/configuracion","Modelo_F1C_"&NOMBRE_FICHERO)
	lvDatos.Clear
	
	
	If listaconfiguracion.Get(0)="0" Then
		cbled.Checked=False
	End If
	
	If listaconfiguracion.Get(0)="1" Then
		cbled.Checked=True
	End If
			'For i=0 To 4
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("C.INCI.ALAS 1   Giro: "&lista1.Get(0)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("CORTE.MOTOR 2   Giro: "&lista1.Get(1)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("INC.STABILO 3   Giro: "&lista1.Get(2)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("TIMON DIREC 4   Giro: "&lista1.Get(3)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("FLAP-FOLDER 5   Giro: "&lista1.Get(4)&"  INICIO")
				
		'	Next
			
		'	For i=5 To 9 
							    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("C.INCI.ALAS 1   Giro: "&lista1.Get(5)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("CORTE.MOTOR 2   Giro: "&lista1.Get(6)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("INC.STABILO 3   Giro: "&lista1.Get(7)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("TIMON DIREC 4   Giro: "&lista1.Get(8)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("FLAP-FOLDER 5   Giro: "&lista1.Get(9)&"  RDT")
			'Next
			
			Log("Tamaño Lista "&lista1.Size)
			
			For i=10 To lista1.Size-1 Step 4
				tiempo_aux=lista1.Get(i) 
				tiempo_minimo=lista1.get(i)
				Log("Tiempo actual " &tiempo_minimo)
				tipo_tiempo_aux=lista1.Get(i+1)
				servo_aux=lista1.Get(i+2)
				
				If tipo_tiempo_aux=0 Then
					tiempo_minimo=tiempo_minimo
				
				End If
				
				If tipo_tiempo_aux=1 Then
					tiempo_minimo=tiempo_minimo*10
				
				End If
				
				If tipo_tiempo_aux=2 Then
					tiempo_minimo=tiempo_minimo*600
				
				End If
				
				
				
				
				If servo_aux=1 Then
					servo_aux_nombre="C.INCI.ALAS 1"
				End If 
				If servo_aux=2 Then
					servo_aux_nombre="CORTE.MOTOR 2"
				End If 
				If servo_aux=3 Then
					servo_aux_nombre="INC.STABILO 3"
				End If 
				If servo_aux=4 Then
					servo_aux_nombre="TIMON DIREC 4"
				End If 
				If servo_aux=5 Then
				servo_aux_nombre="FLAP-FOLDER 5"
				End If 
								
				
				
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
				lvDatos.AddSingleLine(servo_aux_nombre&"   Giro: "&giro_aux&"    Tiempo: "&tiempo_aux&"  Tipo tiempo: "&tipo_tiempo_aux)
			
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
		lbservotxt.Text=""
		btOK.Enabled=False
		btOK.Visible=False
		Button1.Enabled=True
		Button1.Visible=True
		contador_lineas=12
		Panel1.Visible=True
				Button1.Enabled=False
		Button1.Visible=True
		spTiempo.Enabled=False
		etTiempo.Enabled=False
		etGiro.Enabled=False
	Panel2.Visible=True
	Label5.Visible=True
	lbNumeroServo.Visible=False'true
	lbservotxt.Visible=True
	
	End If	
	

	
	
	ime1.HideKeyboard
	
	
	Catch
'		NOMBRE_FICHERO=etFichero.Text
	
		If File.Exists(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO)=True Then
			lista1.Clear
			'ToastMessageShow("no existe",False)
			'	NOMBRE_FICHERO=etFichero.Text & " " & config
			lbModelo.Text=NOMBRE_FICHERO
			strModelo="Modelo_F1B_"&NOMBRE_FICHERO
lvDatos.Clear
Panel1.Visible=False
		spServo.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label9.Visible=False
		Label8.Visible=True 
			Panel2.Visible=True
	Label5.Visible=True
	lbNumeroServo.Visible=False'true
	lbservotxt.Visible=True
	tiempo_minimo=0
		lbNumeroServo.Text="C.I.ALA"
		lbservotxt.Text="C.I.ALA"
		btOK.Enabled=True
		btOK.Visible=False
		Button1.Enabled=False
		Button1.Visible=False
		Button2.Visible=False
		Button2.Enabled=False
		etGiro.Enabled=True
				btOK.Visible=True
		'etGiro.Enabled=True
		tiempo_actual=0
			File.WriteList(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO,lista1)
	contador_lineas=1
		End If
	
	ime1.HideKeyboard

	End Try



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
		'ToastMessageShow(valor,False)

		Panel1.Visible=False
	If dial.Response=-1 Then
		'ToastMessageShow("ok",False)
		
	
	End If 

End Sub

Sub spServo_ItemClick (Position As Int, Value As Object)
Dim nom_ser As String
	If Position=0 Then
	
	
	Else 
	servo=Position
	If servo=1 Then
		nom_ser="C.I.ALA"
	End If
	If servo=2 Then
		nom_ser="MOTOR"
	End If
	If servo=3 Then
		nom_ser="STABILO"
	End If
	If servo=4 Then
		nom_ser="TIMON DIREC"
	End If
	If servo=5 Then
		nom_ser="FLAP-FOLDER"
	End If
	lbNumeroServo.Text=nom_ser
	lbservotxt.Text=nom_ser
	spTiempo.Enabled=True
	'spServo.RemoveAt(0)
	End If
End Sub

Sub spconfig_ItemClick (Position As Int, Value As Object)
Dim numpos As Int
Try 

	If Value="PULSE AQUI" Then
	
		btOK.Visible=False
	Else 
	
	lvDatos.Clear
	config=" "&Value

NOMBRE_FICHERO=strfichero&config
 etFichero.Text=NOMBRE_FICHERO
	spconfig.Enabled=False
	
		lista1.Clear
			'ToastMessageShow("no existe",False)
			'	NOMBRE_FICHERO=etFichero.Text & " " & config
			lbModelo.Text=NOMBRE_FICHERO
			strModelo="Modelo_F1C_"&NOMBRE_FICHERO
lvDatos.Clear
Panel1.Visible=False
		spServo.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label9.Visible=False
		Label8.Visible=True 
			Panel2.Visible=True
	Label5.Visible=True
	lbNumeroServo.Visible=False'true
	lbservotxt.Visible=True
	tiempo_minimo=0
		lbNumeroServo.Text="C.I.ALA"
		lbservotxt.Text="C.I.ALA"
		btOK.Enabled=True
		btOK.Visible=False
		Button1.Enabled=False
		Button1.Visible=False
		Button2.Visible=False
		Button2.Enabled=False
		etGiro.Enabled=True
				btOK.Visible=True
		'etGiro.Enabled=True
		tiempo_actual=0
			File.WriteList(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO,lista1)
	contador_lineas=1
		
	End If
	ime1.HideKeyboard
	Catch
		NOMBRE_FICHERO=etFichero.Text
	
		If File.Exists(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO)=True Then
			lista1.Clear
			'ToastMessageShow("no existe",False)
			'	NOMBRE_FICHERO=etFichero.Text & " " & config
			lbModelo.Text=NOMBRE_FICHERO
			strModelo="Modelo_F1C_"&NOMBRE_FICHERO
lvDatos.Clear
Panel1.Visible=False
		spServo.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label9.Visible=False
		Label8.Visible=True 
			Panel2.Visible=True
	Label5.Visible=True
	lbNumeroServo.Visible=False'true
	lbservotxt.Visible=True
	tiempo_minimo=0
		lbNumeroServo.Text="C.I.ALA"
		lbservotxt.Text="C.I.ALA"
		btOK.Enabled=True
		btOK.Visible=False
		Button1.Enabled=False
		Button1.Visible=False
		Button2.Visible=False
		Button2.Enabled=False
		etGiro.Enabled=True
				btOK.Visible=True
		'etGiro.Enabled=True
		tiempo_actual=0
			File.WriteList(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO,lista1)
	contador_lineas=1
		End If
	
	ime1.HideKeyboard

	End Try




listaconfiguracion.Set(1,NOMBRE_FICHERO)

				File.WriteList(File.DirRootExternal & "/Consola/F1C/configuracion","Modelo_F1C_"&NOMBRE_FICHERO,listaconfiguracion)


End Sub

Sub Button2_Click

If spServo.SelectedItem="PULSE AQUI" Then
'ToastMessageShow("VALOR NO VALIDO",True)
End If
Dim aux_valor_tiempo As String
Dim aux_valor_giro As String
Dim pos_valor As Int
Dim pos_mod_aux As String
Dim aux_valor_giro_inicio As String

If posicion_mod<10 Then




		If giro<=9 Then
'			aux_valor_giro_inicio="0"&giro
'			Log("aux_valor_giro: "&aux_valor_giro_inicio)
			aux_valor_giro="00"&giro
			lista1.set(posicion_mod,aux_valor_giro)
		End If

		If giro <=99 Then
			If giro>9 Then
				'aux_valor_giro="0"&giro
					aux_valor_giro="0"&giro
			lista1.set(posicion_mod,aux_valor_giro)
			End If
		End If

		If giro >99 Then
			aux_valor_giro=giro
			lista1.set(posicion_mod,aux_valor_giro)
		End If

	spServo.Visible=True
		spTiempo.Visible=True
		Label2.Visible=True
		Panel3.Visible=True
		Label4.Visible=True
	Panel4.Visible=True
		etTiempo.Visible=True
		Label8.Visible=False
		Button1.Enabled=False
		etGiro.Enabled=False
		 
		 
		 


	File.WriteList(File.DirRootExternal & "/Consola/F1C" ,"Modelo_F1C_"&NOMBRE_FICHERO,lista1)
	lvDatos.Clear
	
				'For i=0 To 4
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("C.INCI.ALAS 1   Giro: "&lista1.Get(0)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("CORTE.MOTOR 2   Giro: "&lista1.Get(1)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("INC.STABILO 3   Giro: "&lista1.Get(2)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("TIMON DIREC 4   Giro: "&lista1.Get(3)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("FLAP-FOLDER 5   Giro: "&lista1.Get(4)&"  INICIO")
				
			'Next
			
			'For i=5 To 9 
							    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("C.INCI.ALAS 1   Giro: "&lista1.Get(5)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("CORTE.MOTOR 2   Giro: "&lista1.Get(6)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("INC.STABILO 3   Giro: "&lista1.Get(7)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("TIMON DIREC 4   Giro: "&lista1.Get(8)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("FLAP-FOLDER 5   Giro: "&lista1.Get(9)&"  RDT")
		'	Next
			
	
	
'				For i=0 To 4
'			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
'				lvDatos.AddSingleLine("Servo "&(i+1)&"   Giro: "&lista1.Get(i)&"  INICIO")
'			Next
'			
'			For i=5 To 9 
'			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
'				lvDatos.AddSingleLine("Servo "&(i-4)&"   Giro: "&lista1.Get(i)&"  RDT")
'			Next
			
			For i=10 To lista1.Size-1 Step 4
				tiempo_aux=lista1.Get(i) 
				tipo_tiempo_aux=lista1.Get(i+1)
				servo_aux=lista1.Get(i+2)
				
	If servo_aux=1 Then
					servo_aux_nombre="C.INCI.ALAS 1"
				End If 
				If servo_aux=2 Then
					servo_aux_nombre="CORTE.MOTOR 2"
				End If 
				If servo_aux=3 Then
					servo_aux_nombre="INC.STABILO 3"
				End If 
				If servo_aux=4 Then
					servo_aux_nombre="TIMON DIREC 4"
				End If 
				If servo_aux=5 Then
				servo_aux_nombre="FLAP-FOLDER 5"
				End If 
								
				
				giro_aux=lista1.Get(i+3)
				If tipo_tiempo_aux="0" Then
				tipo_tiempo="DECIMAS"
				tiempo_aux=tiempo_aux
				End If
'				
				If tipo_tiempo_aux=1 Then
				tipo_tiempo="SEGUNDOS"
			'	tiempo_aux=tiempo_aux/10
				End If
				
				If tipo_tiempo_aux=2 Then
				tipo_tiempo="MINUTOS"
			'	tiempo_aux=tiempo_aux/600
				End If
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine(servo_aux_nombre&"   Giro: "&giro_aux&"    Tiempo: "&tiempo_aux&"  Tipo tiempo: "&tipo_tiempo)
			Next

	Button2.Enabled=False
Button2.Visible=False
Button1.Enabled=True
Button1.Visible=True


lbNumeroServo.Text=""
lbservotxt.Text=""
spServo.SelectedIndex=0
etGiro.Text=""
etTiempo.Text=""
spTiempo.SelectedIndex=0
spServo.Background=BC1
	ime1.HideKeyboard
	Panel1.Visible=True
	Label5.Visible=True
	Label9.Visible=False
	lbNumeroServo.Visible=False'true
	lbservotxt.Visible=True
	
	
	etGiro.Enabled=False
	etTiempo.Enabled=False
	spTiempo.Enabled=False
	
		
		
End If
etTiempo_2


End Sub

Sub lvDatos_ItemClick (Position As Int, Value As Object)
Try
tiempo_ante_lv=0
	Button2.Enabled=True
Button2.Visible=True
Button1.Enabled=False
Button1.Visible=False
btOK.Enabled=False
btOK.Visible=False
Dim str As String
Label8.Visible=False
Label9.Visible=False
etGiro.Enabled=True
etTiempo.Enabled=True
spTiempo.Enabled=True
Panel2.Visible=True
Dim lv_tipo As String
Dim lv_tipo1  As Int



	If Position<=4 Then
		Button2.Enabled=True
Button2.Visible=True
	Label5.Visible=True
	lbNumeroServo.Visible=False'true
	lbservotxt.Visible=True
		spServo.Visible=False
		Panel1.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label8.Visible=True
		etGiro.Enabled=True

		posicion_mod=Position
		str=Value
		servo_lv=str.SubString2(12,13)
		giro_lv=str.SubString2(22,25)
		lbNumeroServo.text=servo_lv
		
			If servo_lv=1 Then
					lbservotxt.Text="C.I.ALA"
				End If 
				If servo_lv=2 Then
					lbservotxt.Text="MOTOR"
				End If 
				If servo_lv=3 Then
					lbservotxt.Text="STABILO"
				End If 
				If servo_lv=4 Then
					lbservotxt.Text="TIMON DIREC"
				End If 
				If servo_lv=5 Then
				lbservotxt.Text="FLAP-FOLDER"
				End If 
		
		Log("giro_lv: "&giro_lv)
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
		
		
		
		'ToastMessageShow("giro "&giro_lv,True)
		'ToastMessageShow("servo "&servo_lv,True)
flag_button2=True
'Button2.Enabled=False
	End If
	
	If Position>=5 Then
		If Position<=9 Then
			Label5.Visible=True
	lbNumeroServo.Visible=False'true
	lbservotxt.Visible=True
				Panel1.Visible=False
			spServo.Visible=False
		spTiempo.Visible=False
		Label2.Visible=False
		Panel3.Visible=False
		Label4.Visible=False
		Panel4.Visible=False
		etTiempo.Visible=False
		Label9.Visible=True
		etGiro.Enabled=True
	
			posicion_mod=Position
			str=Value
			servo_lv=str.SubString2(12,13)
		giro_lv=str.SubString2(22,25)
			lbNumeroServo.text=servo_lv
				If servo_lv=1 Then
					lbservotxt.Text="C.I.ALA"
				End If 
				If servo_lv=2 Then
					lbservotxt.Text="MOTOR"
				End If 
				If servo_lv=3 Then
					lbservotxt.Text="STABILO"
				End If 
				If servo_lv=4 Then
					lbservotxt.Text="TIMON DIREC"
				End If 
				If servo_lv=5 Then
				lbservotxt.Text="FLAP-FOLDER"
				End If 
		
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
		
		
	'ToastMessageShow("giro "&giro_lv,True)
	'	ToastMessageShow("servo "&servo_lv,True)
		flag_button2=True
'Button2.Enabled=False
		End If
	End If

	If Position>=10 Then
	Panel1.Visible=True
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
		lbservotxt.Text=""


	
		posicion_mod=Position
		mod_tiempo(Position)
		
		str=Value
	'	ToastMessageShow(str,True)
	'	ToastMessageShow(str.SubString2(37,40),True)
		lv_tipo=str.SubString2(55,56)
		'ToastMessageShow(str.SubString2(55,56),True)
	'	ToastMessageShow("tipo tiempo " &lv_tipo,True)
		If lv_tipo="D" Then
			lv_tipo1=1
			tipo_tiempo="DECIMAS"
		End If
		
		If lv_tipo="S" Then
			lv_tipo1=2
			tipo_tiempo="SEGUNDOS"
		End If
		
		If lv_tipo="M" Then
			lv_tipo1=3
			tipo_tiempo="MINUTOS"
		End If
		
		tiempo_lv =str.SubString2(37,40)
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
		
		
		servo_lv=str.SubString2(12,13)
		servo=servo_lv
		lbNumeroServo.Text=servo
		
			If servo=1 Then
					lbservotxt.Text="C.I.ALA"
				End If 
				If servo=2 Then
					lbservotxt.Text="MOTOR"
				End If 
				If servo=3 Then
					lbservotxt.Text="STABILO"
				End If 
				If servo=4 Then
					lbservotxt.Text="TIMON DIREC"
				End If 
				If servo=5 Then
				lbservotxt.Text="FLAP-FOLDER"
				End If 
		
		
		
		
		
		spServo.SelectedIndex=servo_lv
		spTiempo.SelectedIndex=lv_tipo1
	'	spServo.SelectedItem="Servo "&servo_lv
		
		giro_lv=str.SubString2(22,25)
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
		
		tipo_tiempo_aux1=str.SubString2(55,56)
'		If tipo_tiempo_aux1="D" Then
'			tipo_tiempo="DECIMAS"
'		End If
'		
'		If tipo_tiempo_aux1="S" Then
'			tipo_tiempo="SEGUNDOS"
'		End If
'		
'		If tipo_tiempo_aux1="M" Then
'			tipo_tiempo="MINUTOS"
'			
'		End If

	End If
	'ToastMessageShow(Value,True)
	Catch 
	
	End Try

End Sub

Sub mod_tiempo(pos As Int)
Dim pos_valor As Int
Dim pos_mod_aux As String
Dim size As Int
size = lvDatos.size-1
If pos=10 Then
If pos=size Then
pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+10
'tiempo_ante_lv=0
'tiempo_post_lv=0
'servo_ante_lv=0
'servo_post_lv=0
'tiempo_post_lv=lista1.Get(pos_valor+4)
Else

	pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+10
tiempo_ante_lv=0
tiempo_post_lv=lista1.Get(pos_valor+4)
tIPO_POST_LV=lista1.Get(pos_valor+5)
servo_ante_lv=0
		servo_post_lv=lista1.Get(pos_valor+6)
End If
End If


If pos<20 Then
	If pos>10 Then
	If pos=size Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+10
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		TIPO_ANTE_LV=lista1.Get(pos_valor-3)
			servo_ante_lv=lista1.Get(pos_valor-2)
			tiempo_post_lv=9999999999999999999
			servo_post_lv="NO"
	Else
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+10
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		tiempo_post_lv=lista1.Get(pos_valor+4)
		tIPO_POST_LV=lista1.Get(pos_valor+5)
		servo_ante_lv=lista1.Get(pos_valor-2)
		servo_post_lv=lista1.Get(pos_valor+6)
End If
End If

End If




If pos<30 Then
	If pos>=20 Then
	If pos=size Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+50
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		TIPO_ANTE_LV=lista1.Get(pos_valor-3)
			servo_ante_lv=lista1.Get(pos_valor-2)
			tiempo_post_lv="NO"
			servo_post_lv="NO"
	Else
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+50
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		tiempo_post_lv=lista1.Get(pos_valor+4)
		tIPO_POST_LV=lista1.Get(pos_valor+5)
		servo_ante_lv=lista1.Get(pos_valor-2)
		servo_post_lv=lista1.Get(pos_valor+6)
End If
End If

End If






If pos<40 Then
	If pos>=30 Then
	If pos=size Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+90
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		TIPO_ANTE_LV=lista1.Get(pos_valor-3)
			servo_ante_lv=lista1.Get(pos_valor-2)
			tiempo_post_lv="NO"
			servo_post_lv="NO"
	Else
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+90
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		tiempo_post_lv=lista1.Get(pos_valor+4)
		tIPO_POST_LV=lista1.Get(pos_valor+5)
		servo_ante_lv=lista1.Get(pos_valor-2)
		servo_post_lv=lista1.Get(pos_valor+6)
End If
End If

End If








If pos<50 Then
	If pos>=40 Then
	If pos=size Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+130
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		TIPO_ANTE_LV=lista1.Get(pos_valor-3)
			servo_ante_lv=lista1.Get(pos_valor-2)
			tiempo_post_lv="NO"
			servo_post_lv="NO"
	Else
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+130
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		tiempo_post_lv=lista1.Get(pos_valor+4)
		tIPO_POST_LV=lista1.Get(pos_valor+5)
		servo_ante_lv=lista1.Get(pos_valor-2)
		servo_post_lv=lista1.Get(pos_valor+6)
End If
End If

End If




If pos<60 Then
	If pos>=50 Then
	If pos=size Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+170
		tiempo_ante_lv=lista1.Get(pos_valor-4)
		TIPO_ANTE_LV=lista1.Get(pos_valor-3)
			servo_ante_lv=lista1.Get(pos_valor-2)
			tiempo_post_lv="NO"
			servo_post_lv="NO"
	Else
'		pos_mod_aux=posicion_mod.SubString2(1,2)
'		pos_valor=(pos_mod_aux*4)+170
'		tiempo_ante_lv=lista1.Get(pos_valor-4)
'		tiempo_post_lv=lista1.Get(pos_valor+4)
'		servo_ante_lv=lista1.Get(pos_valor-2)
'		servo_post_lv=lista1.Get(pos_valor+6)
End If
End If

End If


End Sub

Sub lvDatos_ItemLongClick (Position As Int, Value As Object)

Dim aux_valor_tiempo As String
Dim aux_valor_giro As String
Dim pos_valor As Int
Dim pos_mod_aux As String
Dim aux_valor_giro_inicio As String
posicion_mod=Position
	If Position<10 Then
	
	
	Else
	
			lvDatos.RemoveAt(Position)
			
	

If posicion_mod<20 Then
	If posicion_mod>=10 Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+10
'	ToastMessageShow("Linea: "&posicion_mod&" Unidad: "&pos_mod_aux&" Valor en lista: "&pos_valor,True)
	If posicion_mod= lvDatos.Size Then
	For i=0 To 3
		lista1.RemoveAt(pos_valor-1)
	Next
	
	Else
		lista1.RemoveAt(pos_valor)
	lista1.RemoveAt(pos_valor)
	lista1.RemoveAt(pos_valor)
	lista1.RemoveAt(pos_valor)
End If
	
	End If
End If
			
			
			
			
			
			
			
			borrar_datos(20,30,50)

borrar_datos(30,40,90)

borrar_datos(40,50,130)

borrar_datos(50,60,170)


Dim test As Int
		
			For i=0 To lista1.Size-1 
			
			Log(lista1.Get(i))
			
			Next
			File.WriteList(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO,lista1)
			If Position=10 Then
			tiempo_minimo=0
			Else
			tiempo_minimo=lista1.Get(lista1.Size-4)
			
			test=lista1.Get(lista1.Size-3)

				
				If test=0 Then
					tiempo_minimo=tiempo_minimo
				
				End If
				
				If test=1 Then
					tiempo_minimo=tiempo_minimo*10
				
				End If
				
				If test=2 Then
					tiempo_minimo=tiempo_minimo*600
				
				End If
			
			
	End If
			Log("Ultimo tiemoi "&tiempo_minimo)
			End If
			
								lbNumeroServo.Text=""
								lbservotxt.Text=""
spServo.SelectedIndex=0
etGiro.Text=""
etTiempo.Text=""
spTiempo.SelectedIndex=0
spServo.Background=BC1
		Button1.Enabled=False
		Button1.Visible=True
		Button2.Visible=False
		spTiempo.Enabled=False
		etTiempo.Enabled=False
		etGiro.Enabled=False
	
End Sub

Sub etTiempo_TextChanged (Old As String, New As String)
Try
If New>0 Then
	If New<=999 Then
etGiro.Enabled=True
Else 
etGiro.Enabled=False
etTiempo.Text=""
End If
End If
Catch
End Try
End Sub

Sub etTiempo_1
Try
Dim ret As Boolean
Dim str As String
Dim aux_tiempo As  Int
'If lvDatos.Size-1<10 Then
'
'
'Else
'str =lvDatos.GetItem(lvDatos.Size-1)
'aux_tiempo=str.SubString2(33,36)
'



tiempo_actual=etTiempo.Text
Log("Tiempo Actual sin convertir: "&tiempo_actual)
If tipo_tiempo="DECIMAS" Then
tiempo_actual=tiempo_actual
Log("Tiempo Actual decimas convertir decimas: "&tiempo_actual)
End If 

If tipo_tiempo="SEGUNDOS" Then
tiempo_actual=tiempo_actual*10
Log("Tiempo Actual segundos convertir decimas: "&tiempo_actual)
End If 
If tipo_tiempo="MINUTOS" Then
tiempo_actual=tiempo_actual*600
Log("Tiempo Actual minutos convertir decimas: "&tiempo_actual)
End If 

Log("Tiempo minimo: "&tiempo_minimo)


If etTiempo.Text=0 Then
etTiempo.Text=""


Else

	If tiempo_actual=tiempo_minimo Then
	
	Log("tiempo =")
	ret=mirar_tiempo_servo(servo,tiempo_actual)
		If ret=True Then
			grabar
			ret=False
		End If

	End If


	If tiempo_actual>tiempo_minimo Then

		tiempo=etTiempo.Text
		tiempo_minimo=tiempo_actual
		Log("INT de tiempo:  " & tiempo_minimo)
		etGiro.Enabled=True
		grabar
	
	End If
	If tiempo_actual<tiempo_minimo Then

		etTiempo.Text=""
		Msgbox("Tiempo menor que el anterior","ERROR")
		'ToastMessageShow("Tiempo Menor que el anterior",True)

	End If
	



End If 
Catch

End Try
End Sub

Sub etTiempo_2
Try
ex=True 
Dim ret As Boolean
Dim str As String
Dim aux_tiempo As  Int

'If lvDatos.Size-1<10 Then
'
'
'Else
'str =lvDatos.GetItem(lvDatos.Size-1)
'aux_tiempo=str.SubString2(33,36)
'
Log("tipo tiempo Anterior modificaar "&TIPO_ANTE_LV)


Log("tipo tiempo Posterior modificaar "&tIPO_POST_LV)


tiempo_actual=etTiempo.Text
If tipo_tiempo="DECIMAS" Then
tiempo_actual=tiempo_actual
Log("Tiempo Actual decimas convertir decimas: "&tiempo_actual)
End If 

If tipo_tiempo="SEGUNDOS" Then
tiempo_actual=tiempo_actual*10
Log("Tiempo Actual segundos convertir decimas: "&tiempo_actual)
End If 
If tipo_tiempo="MINUTOS" Then
tiempo_actual=tiempo_actual*600
Log("Tiempo Actual minutos convertir decimas: "&tiempo_actual)
End If 



If TIPO_ANTE_LV=0 Then
tiempo_ante_lv=tiempo_ante_lv
Log("Tiempo ant decimas convertir decimas: "&tiempo_ante_lv)
flag_multi=True
End If 

If TIPO_ANTE_LV=1 Then
tiempo_ante_lv=tiempo_ante_lv*10
Log("Tiempo ante segundos convertir decimas: "&tiempo_ante_lv)
End If 
If TIPO_ANTE_LV=2 Then
tiempo_ante_lv=tiempo_ante_lv*600
Log("Tiempo ante minutos convertir decimas: "&tiempo_ante_lv)
flag_multi=True
End If 


If tIPO_POST_LV=0 Then
tiempo_post_lv=tiempo_post_lv
Log("Tiempo post decimas convertir decimas: "&tiempo_post_lv)
flag_multi=True
End If 

If tIPO_POST_LV=1 Then
tiempo_post_lv=tiempo_post_lv*10
Log("Tiempo post segundos convertir decimas: "&tiempo_post_lv)
End If 
If tIPO_POST_LV=2 Then
tiempo_post_lv=tiempo_post_lv*600
Log("Tiempo post minutos convertir decimas: "&tiempo_post_lv)
End If 


If tiempo_actual=tiempo_ante_lv Then
	Log("tiempo =")
	ret=mirar_tiempo_servo(servo,tiempo_actual)
	Log("returnt mismo tiempo servo "& ret)
	If ret=True Then
		mod_datos_button2
		ret=False
	End If
Else If tiempo_actual=tiempo_post_lv Then
	Log("tiempo =")
	ret=mirar_tiempo_servo(servo,tiempo_actual)
		Log("returnt mismo tiempo servo "& ret)
	If ret=True Then
		mod_datos_button2
		ret=False
	End If
ex=False
'	Exit Sub
End If

If ex=True Then

If tiempo_actual>tiempo_ante_lv Then

	If tiempo_actual<tiempo_post_lv Then
	 		tiempo=tiempo_actual
'			ret=mirar_tiempo_servo(servo,tiempo_actual)
'				If ret=True Then
'					mod_datos_button2
'					ret=False
'				End If
				mod_datos_button2
			Else
					Msgbox("Tiempo actual mayor que el posterior!!!!!","ERROR")
					lbNumeroServo.Text=""
					lbservotxt.Text=""
					spServo.SelectedIndex=0
					etGiro.Text=""
					etTiempo.Text=""
					spTiempo.SelectedIndex=0
					spServo.Background=BC1
							Button1.Enabled=False
							Button1.Visible=True
							Button2.Visible=False
							spTiempo.Enabled=False
							etTiempo.Enabled=False
							etGiro.Enabled=False
	End If
Else

		Msgbox("Tiempo actual menor que el anterior","ERROR")
		lbNumeroServo.Text=""
		lbservotxt.Text=""
		spServo.SelectedIndex=0
		etGiro.Text=""
		etTiempo.Text=""
		spTiempo.SelectedIndex=0
		spServo.Background=BC1
				Button1.Enabled=False
				Button1.Visible=True
						Button2.Visible=False

				spTiempo.Enabled=False
				etTiempo.Enabled=False
				etGiro.Enabled=False
	
End If
End If


Catch

End Try
End Sub

Sub mod_datos_button2
Try
Dim aux_valor_tiempo As String
Dim aux_valor_giro As String
Dim pos_valor As Int
Dim pos_mod_aux As String
Dim aux_valor_giro_inicio As String
If tipo_tiempo="DECIMAS" Then
tiempo=tiempo
Log("Tiempo Actual decimas: "&tiempo)
End If 

'If tipo_tiempo="SEGUNDOS" Then
'tiempo=tiempo/10
'Log("Tiempo Actual segundos : "&tiempo)
'End If 
'If tipo_tiempo="MINUTOS" Then
'tiempo=tiempo/600
'Log("Tiempo Actual minutos: "&tiempo)
'End If


If posicion_mod<20 Then
	If posicion_mod>=10 Then
	If tipo_tiempo="SEGUNDOS" Then
tiempo=tiempo/10
Log("Tiempo Actual segundos : "&tiempo)
End If 
If tipo_tiempo="MINUTOS" Then
tiempo=tiempo/600
Log("Tiempo Actual minutos: "&tiempo)
End If

		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+10
'	ToastMessageShow("Linea: "&posicion_mod&" Unidad: "&pos_mod_aux&" Valor en lista: "&pos_valor,True)
	
		If tiempo<=9 Then
			'aux_valor_tiempo="00"&tiempo
	aux_valor_tiempo="00"&tiempo
			lista1.Set(pos_valor,aux_valor_tiempo)
		End If

		If tiempo <=99 Then
			If tiempo>9 Then
				'aux_valor_tiempo="0"&tiempo
				aux_valor_tiempo="0"&tiempo
				lista1.Set(pos_valor,aux_valor_tiempo)
			End If
		End If

		If tiempo >99 Then
			aux_valor_tiempo=tiempo

			lista1.Set(pos_valor,aux_valor_tiempo)
		End If
	
'------------------------------------------------------------	
		If tipo_tiempo="DECIMAS" Then
			lista1.set(pos_valor+1,"0")
		End If
		If tipo_tiempo="SEGUNDOS" Then
			lista1.set(pos_valor+1,"1")
		End If
		If tipo_tiempo="MINUTOS" Then
			lista1.set(pos_valor+1,"2")
		End If
'---------------------------------------	
	lista1.set(pos_valor+2,servo)
	
'-----------------------------------------
		If giro<=9 Then
		'	aux_valor_giro="0"&giro
					giro="00"&giro
	
			lista1.set(pos_valor+3,giro)
		End If

		If giro <=99 Then
			If giro>9 Then
				'aux_valor_giro="0"&giro
							giro="0"&giro

			lista1.set(pos_valor+3,giro)
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








	File.WriteList(File.DirRootExternal & "/Consola/F1C" ,"Modelo_F1C_"&NOMBRE_FICHERO,lista1)
	lvDatos.Clear
	
	
'	
'	
'				For i=0 To 4
'			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
'				lvDatos.AddSingleLine("Servo "&(i+1)&"   Giro: "&lista1.Get(i)&"  INICIO")
'			Next
'			
'			For i=5 To 9 
'			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
'				lvDatos.AddSingleLine("Servo "&(i-4)&"   Giro: "&lista1.Get(i)&"  RDT")
'			Next
				'For i=0 To 4
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("C.INCI.ALAS 1   Giro: "&lista1.Get(0)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("CORTE.MOTOR 2   Giro: "&lista1.Get(1)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("INC.STABILO 3   Giro: "&lista1.Get(2)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("TIMON DIREC 4   Giro: "&lista1.Get(3)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("FLAP-FOLDER 5   Giro: "&lista1.Get(4)&"  INICIO")
				
			'Next
			
			'For i=5 To 9 
							    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("C.INCI.ALAS 1   Giro: "&lista1.Get(5)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("CORTE.MOTOR 2   Giro: "&lista1.Get(6)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("INC.STABILO 3   Giro: "&lista1.Get(7)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("TIMON DIREC 4   Giro: "&lista1.Get(8)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("FLAP-FOLDER 5   Giro: "&lista1.Get(9)&"  RDT")
			
			For i=10 To lista1.Size-1 Step 4
				tiempo_aux=lista1.Get(i) 
				tipo_tiempo_aux=lista1.Get(i+1)
				servo_aux=lista1.Get(i+2)
				
If servo_aux=1 Then
					servo_aux_nombre="C.INCI.ALAS 1"
				End If 
				If servo_aux=2 Then
					servo_aux_nombre="CORTE.MOTOR 2"
				End If 
				If servo_aux=3 Then
					servo_aux_nombre="INC.STABILO 3"
				End If 
				If servo_aux=4 Then
					servo_aux_nombre="TIMON DIREC 4"
				End If 
				If servo_aux=5 Then
				servo_aux_nombre="FLAP-FOLDER 5"
				End If 
								
				
				giro_aux=lista1.Get(i+3)
				If tipo_tiempo_aux=0 Then
				tipo_tiempo_aux="DECIMAS"
				End If
'				
				If tipo_tiempo_aux=1 Then
				tipo_tiempo_aux="SEGUNDOS"
				End If
				
				If tipo_tiempo_aux=2 Then
				tipo_tiempo_aux="MINUTOS"
				End If
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine(servo_aux_nombre&"   Giro: "&giro_aux&"    Tiempo: "&tiempo_aux&"  Tipo tiempo: "&tipo_tiempo_aux)
'			
			Next
tiempo_minimo=aux_valor_tiempo
	Button2.Enabled=False
Button2.Visible=False
Button1.Enabled=True
Button1.Visible=True


lbNumeroServo.Text=""
lbservotxt.Text=""
spServo.SelectedIndex=0
etGiro.Text=""
etTiempo.Text=""
spTiempo.SelectedIndex=0
spServo.Background=BC1
	ime1.HideKeyboard
	Panel1.Visible=True
	Label5.Visible=True
	lbNumeroServo.Visible=False'true
	lbservotxt.Visible=True
	
	
	etGiro.Enabled=False
	etTiempo.Enabled=False
	spTiempo.Enabled=False
	
	
Catch

End Try

End Sub

Sub mirar_tiempo_servo(servo_actual As Int, tiempo_actual1 As Int) As Boolean
Dim numerolineas As Int
Dim aux_servo As Int
Dim aux_tiempo As Int
Dim aux_tipo As String
numerolineas = lvDatos.Size
Dim i As Int
Dim str As String
Dim num As Int
num =numerolineas-5
Dim servo_actual_nombre As String



If servo_actual=1 Then
					servo_actual_nombre="C.INCI.ALAS 1"
				End If 
				If servo_actual=2 Then
					servo_actual_nombre="CORTE.MOTOR 2"
				End If 
				If servo_actual =3 Then
					servo_actual_nombre="INC.STABILO 3"
				End If 
				If servo_actual=4 Then
					servo_actual_nombre="TIMON DIREC 4"
				End If 
				If servo_actual=5 Then
				servo_actual_nombre="FLAP-FOLDER 5"
				End If 

										
				
Log(servo_actual_nombre &"tiempo Actual "&tiempo_actual1&" Numero_lineas "& numerolineas & "numero lineas-5 "&num)
For  i= num To  numerolineas-1

If i<10 Then


Else

str =lvDatos.GetItem(lvDatos.Size-1)
aux_servo = str.SubString2(12,13)
Log(lvDatos.GetItem(lvDatos.Size-1))
aux_tiempo=str.SubString2(36,40)
aux_tipo=str.SubString2(55,56)
Log("cadena "&str)

Log("aux_servo "&aux_servo)
Log("aux_tiempo "&aux_tiempo)
Log("aux_tipo "&aux_tipo)



If aux_tipo="D" Then
aux_tiempo=aux_tiempo
Log("Tiempo Actual decimas convertir decimas: "&aux_tiempo)
End If 

If aux_tipo="S" Then
aux_tiempo=aux_tiempo*10
Log("Tiempo Actual segundos convertir decimas: "&aux_tiempo)
End If 
If aux_tipo="M" Then
aux_tiempo=aux_tiempo*600
Log("Tiempo Actual minutos convertir decimas: "&aux_tiempo)
End If 







If aux_tiempo>=tiempo_actual1 Then

		If aux_servo=servo_actual Then

			etTiempo.Text=""
			'ToastMessageShow("mismo tiempo y servo",True)
			Msgbox("Mismo TIEMPO y mismo SERVO","ERROR")
				
				ex=False
									lbNumeroServo.Text=""
									lbservotxt.Text=""
						spServo.SelectedIndex=0
						etGiro.Text=""
						etTiempo.Text=""
						spTiempo.SelectedIndex=0
						spServo.Background=BC1
								Button1.Enabled=False
								Button1.Visible=True
								Button2.Visible=False
								spTiempo.Enabled=False
								etTiempo.Enabled=False
								etGiro.Enabled=False
Return False
		Else
					If tipo_tiempo="DECIMAS" Then
				tiempo=tiempo_actual1
				Log("Tiempo final : "&tiempo)
			End If 

			If tipo_tiempo="SEGUNDOS" Then
				tiempo=tiempo_actual1/10
				Log("Tiempo final : "&tiempo)
			End If 
			If tipo_tiempo="MINUTOS" Then
				tiempo=tiempo_actual1/600
				Log("Tiempo final : "&tiempo)
			End If 
			'tiempo=tiempo_actual1
			etGiro.Enabled=True
			ex=True
			Return True
		End If
		
		
'	Else
'	
'	Msgbox("Error","error")
	
End If

End If


Next


End Sub

Sub etGiro_TextChanged (Old As String, New As String)
Try
Dim aux As Int
Dim aux1 As String






If New="" Then

giro=""
Else



If New>0 Then
	If New<=200 Then
		giro=New
	
		Button1.Enabled=True
			If flag_button2=True Then
		Button2.Enabled=True
		Button1.Enabled=False
		flag_button2=False
		End If
		Log("giro_text : "&giro)
	
	Else
		etGiro.Text=""
	End If
Else
	etGiro.Text=""
End If
	End If 
	Catch
	
	End Try
End Sub

Sub Button1_Click
etGiro.ForceDoneButton=False
etTiempo_1

End Sub

Sub grabar 
Dim servo_nombre As String
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

lista1.Add("2")
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
ToastMessageShow("Maximo lineas",True)
Log("numero lineas  "&(contador_lineas-2))
Log("------------------------------")
		Log("------------------------------")

	For i=0 To lista1.Size- 1

		Log(lista1.Get(i))
		
	Next
End If
    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
	
	
					
If servo=1 Then
					servo_nombre="C.INCI.ALAS 1"
				End If 
				If servo=2 Then
					servo_nombre="CORTE.MOTOR 2"
				End If 
				If servo=3 Then
					servo_nombre="INC.STABILO 3"
				End If 
				If servo=4 Then
					servo_nombre="TIMON DIREC 4"
				End If 
				If servo=5 Then
				servo_nombre="FLAP-FOLDER 5"
				End If 
								
	

							
	lvDatos.AddSingleLine(servo_nombre&"   Giro: "&aux_valor_giro&"    Tiempo: "&aux_valor_tiempo&"  Tipo tiempo: "&tipo_tiempo)
	File.WriteList(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO,lista1)
'ToastMessageShow("Servo "&servo&"   Giro: "&aux_valor_giro&"    Tiempo: "&aux_valor_tiempo&"  Tipo tiempo: "&tipo_tiempo,True)
'ToastMessageShow("Servo "&servo&"   Giro: "&aux_valor_giro&"    Tiempo: "&aux_valor_tiempo&"  Tipo tiempo: "&tipo_tiempo,True)

lbNumeroServo.Text=""
lbservotxt.Text=""
spServo.SelectedIndex=0
etGiro.Text=""
etTiempo.Text=""
spTiempo.SelectedIndex=0
spServo.Background=BC1
		Button1.Enabled=False
		Button1.Visible=True
		spTiempo.Enabled=False
		etTiempo.Enabled=False
		etGiro.Enabled=False
Catch

End Try

End Sub

Sub btOK_Click
Dim nom_ser As String
Try

Log("giro en btok " & giro) 

If giro="" Then

Else 

Dim aux_valor_giro As String
'etGiro.ForceDoneButton=False
If contador_lineas<=5 Then
If servo=1 Then
		nom_ser="C.INCI.ALAS"
	End If
	If servo=2 Then
		nom_ser="CORTE MOTOR"
	End If
	If servo=3 Then
		nom_ser="INC.STABILO"
	End If
	If servo=4 Then
		nom_ser="TIMON DIREC"
	End If
	If servo=5 Then
		nom_ser="FLAP-FOLDER"
	End If
	servo=lbNumeroServo.Text
	
				If servo=1 Then
					lbservotxt.Text="C.I.ALA"
				End If 
				If servo=2 Then
					lbservotxt.Text="MOTOR"
				End If 
				If servo=3 Then
					lbservotxt.Text="STABILO"
				End If 
				If servo=4 Then
					lbservotxt.Text="TIMON DIREC"
				End If 
				If servo=5 Then
				lbservotxt.Text="FLAP-FOLDER"
				End If 
	
	
	
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
		lbservotxt.Text="C.I.ALA"
			contador_lineas=contador_lineas+1
	Else
	contador_lineas=contador_lineas+1
	lbNumeroServo.Text=contador_lineas
				If contador_lineas=1 Then
					lbservotxt.Text="C.I.ALA"
				End If 
				If contador_lineas=2 Then
					lbservotxt.Text="MOTOR"
				End If 
				If contador_lineas=3 Then
					lbservotxt.Text="STABILO"
				End If 
				If contador_lineas=4 Then
					lbservotxt.Text="TIMON DIREC"
				End If 
				If contador_lineas=5 Then
				lbservotxt.Text="FLAP-FOLDER"
				End If 
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
		lbservotxt.Text=""
	For i=0 To lista1.Size- 1

		Log(lista1.Get(i))
		'Log("------------------------------")
		'Log("------------------------------")
	Next

	
		contador_lineas=contador_lineas+1
		btOK.Enabled=False
		btOK.Visible=False
		Button1.Enabled=False
		Button1.Visible=True
		spTiempo.Enabled=False
		etTiempo.Enabled=False
		etGiro.Enabled=False
		Panel1.Visible=True
		datos_lv
		ime1.HideKeyboard

	Else
		contador_lineas=contador_lineas+1
		lbNumeroServo.Text=contador_lineas-6
						If contador_lineas-6=1 Then
					lbservotxt.Text="C.I.ALA"
				End If 
				If contador_lineas-6=2 Then
					lbservotxt.Text="MOTOR"
				End If 
				If contador_lineas-6=3 Then
					lbservotxt.Text="STABILO"
				End If 
				If contador_lineas-6=4 Then
					lbservotxt.Text="TIMON DIREC"
				End If 
				If contador_lineas-6=5 Then
				lbservotxt.Text="FLAP-FOLDER"
				End If 
	
	
	End If
	
End If	
	
End If

Log("numero lineas  "&contador_lineas) 

	File.WriteList(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&NOMBRE_FICHERO,lista1)

etGiro.Text=""
giro=""
End If
Catch 

End Try
End Sub

Sub spTiempo_ItemClick (Position As Int, Value As Object)

If Value="PULSE AQUI" Then
	ToastMessageShow("VALOR NO VALIDO",True)
Else
'spTiempo.RemoveAt(0)

	tipo_tiempo=Value
	etTiempo.Enabled=True
'	etGiro.Enabled=True
'		If spTiempo.GetItem(0)="PULSE AQUI" Then
'spTiempo.RemoveAt(0)
'spTiempo.SelectedIndex=Position-1
'End If
End If
End Sub

Sub log_lista
	For i=0 To lista1.Size- 1

Log(lista1.Get(i))

Next


End Sub

Sub datos_lv

'For i=0 To 4
			    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("C.INCI.ALAS 1   Giro: "&lista1.Get(0)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("CORTE.MOTOR 2   Giro: "&lista1.Get(1)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("INC.STABILO 3   Giro: "&lista1.Get(2)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("TIMON DIREC 4   Giro: "&lista1.Get(3)&"  INICIO")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("FLAP-FOLDER 5   Giro: "&lista1.Get(4)&"  INICIO")
				
		'	Next
			
		'	For i=5 To 9 
							    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("C.INCI.ALAS 1   Giro: "&lista1.Get(5)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("CORTE.MOTOR 2   Giro: "&lista1.Get(6)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("INC.STABILO 3   Giro: "&lista1.Get(7)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("TIMON DIREC 4   Giro: "&lista1.Get(8)&"  RDT")
				  lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
				lvDatos.AddSingleLine("FLAP-FOLDER 5   Giro: "&lista1.Get(9)&"  RDT")
			'Next


'
'For i=0 To 4
'    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
'	lvDatos.AddSingleLine(servo_nombre&"   Giro: "&lista1.Get(i)&"  INICIO")
'	'lvDatos.AddSingleLine("servo "&(i+1)&"   giro: "&lista1.Get(i)&"  INICIO")
'
'Next
'For i=5 To 9
'    lvDatos.SingleLineLayout.Label.TextColor=Colors.Black
'
'lvDatos.AddSingleLine("Servo "&(i-4)&"   Giro: "&lista1.Get(i)&"  RDT")
'
'Next



End Sub

Sub tim_Tick
tim.Enabled=False
lbModelo_LongClick

End Sub

Sub lbModelo_LongClick
'	elegir_modelo
	lvDatos.Clear

dlgFileExpl.Initialize(Activity, "/mnt/sdcard/Consola/F1C", "", True, False, "OK")
	
	dlgFileExpl.BorderColor = Colors.RGB(128, 128, 128)
	dlgFileExpl.FolderTextColor = Colors.Black
	dlgFileExpl.FileTextColor1 = Colors.black
	dlgFileExpl.Explorer
	If Not(dlgFileExpl.Selection.Canceled Or dlgFileExpl.Selection.ChosenPath = "") Then
	End If


	
End Sub

Sub Activity_KeyPress(KeyCode As Int) As Boolean
	' Not mandatory, it depends on your app and device
	If dlgFileExpl.IsInitialized Then
		If dlgFileExpl.IsActive Then Return True
	End If
	Return False
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
	'ToastMessageShow("Linea: "&posicion_mod&" Unidad: "&pos_mod_aux&" Valor en lista: "&pos_valor,True)
	
		If tiempo<=9 Then
			aux_valor_tiempo="0"&tiempo
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
			lista1.set(pos_valor+1,"2")
		End If
'---------------------------------------	
	lista1.set(pos_valor+2,servo)
	
'-----------------------------------------
		If giro<=9 Then
			aux_valor_giro="0"&giro
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

Sub borrar_datos (valor_ini As Int, valor_fin As Int, valor_sum As Int)
Dim aux_valor_tiempo As String
Dim aux_valor_giro As String
Dim pos_valor As Int
Dim pos_mod_aux As String
Dim aux_valor_giro_inicio As String



If posicion_mod<valor_fin Then
	If posicion_mod>=valor_ini Then
		pos_mod_aux=posicion_mod.SubString2(1,2)
		pos_valor=(pos_mod_aux*4)+valor_sum
	'ToastMessageShow("Linea: "&posicion_mod&" Unidad: "&pos_mod_aux&" Valor en lista: "&pos_valor,True)
	
	lista1.RemoveAt(pos_valor)
	lista1.RemoveAt(pos_valor)
	lista1.RemoveAt(pos_valor)
	lista1.RemoveAt(pos_valor)
'---------------------------------------	

'-----------------------------------------

	
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
FilesToDelete.AddAll(File.ListFiles(File.DirRootExternal & "/Consola/F1C"))

For I = 0 To FilesToDelete.Size -1
    
    File.Delete(File.DirRootExternal & "/Consola/F1C", FilesToDelete.Get(I))

Next

End Sub

Sub btSalir_LongClick
	
	flag=1
	Log("flag F1C: "&flag)
'Activity.Finish
'	StartActivity(Main)
ExitApplication
End Sub

Sub btComenzar_LongClick
flag=2
	Log("flag F1C: "&flag)

	Activity.Finish
	StartActivity(Main)
End Sub

Sub btgrabar_LongClick
'	If usb1.UsbPresent(1)=usb1.USB_NONE Then
'
'Log("Msgbox - no device")
'		Msgbox("No USB device or accessory detected!", "Error")
'		Log("Msgbox - returned")
'End If
'Log("Checking permission 1")
'	If (usb1.HasPermission(1)) Then	' Ver_2.4
'		'Msgbox(usb1.DeviceInfo(1), "Device Information 1")	' Ver_2.4
'		Dim dev As Int
'		'dev = usb.Open(115200, 1)		' Ver_2.4
'		dev = usb1.Open(9600,1)		' Ver_2.4
'		If dev <> usb1.USB_NONE Then
'			Log("Connected successfully! 1")
			'btnOpen.Enabled = False
			'btnClose.Enabled = True
			'btnSend.Enabled = True			
			'astreams1.Initialize(usb1.GetInputStream, usb1.GetOutputStream, "astreams1")
			'astreams1.Close
			If astreams1.IsInitialized Then
			
			Else
				astreams1.Initialize(usb1.GetInputStream, usb1.GetOutputStream, "astreams1")
			End If
			'valor_d=0
			flag_d=False
			'enviar_configuracion
			flag_lectura=False
			'Leer_fichero
			
			flag_rx=False
	    	send_data("E")
			ProgressDialogShow("Grabando fichero...")
			
'		Else
'			Log("Error opening USB port 1")
'		End If
'	Else
'		usb1.requestPermission(1)  ' Ver_2.4
'	End If
End Sub

Sub Abrir_Fichero_leido (nombre As String)
	lbModelo.Text=nombre
	strfichero=nombre
	lvDatos.Clear
	Dim aux As String
	
			If nombre.Contains("PRUEBA") Then
				strspinner="PRUEBA"
			End If 		
			If nombre.Contains("PRIMERA HORA") Then
				strspinner="PRIMERA HORA"
			End If 	
			If nombre.Contains("VIENTO ALTO") Then
				strspinner="VIENTO ALTO"
			End If 	
			If nombre.Contains("VIENTO MEDIO") Then
				strspinner="VIENTO MEDIO"
			End If 	
			If nombre.Contains("CALMA") Then
				strspinner="CALMA"
			End If 	
			If nombre.Contains("TERMICA") Then
				strspinner="TERMICA"
			End If 	
		AbrirFichero
End Sub

Sub Timer1_tick
	If usb1.UsbPresent(1)=usb1.USB_NONE Then

		btgrabar.Enabled=False
		btLeer.Enabled=False
	Else 
		btgrabar.Enabled=True
		btLeer.Enabled=True
End If



End Sub

Sub openusb
	If usb1.UsbPresent(1)=usb1.USB_NONE Then

Log("Msgbox - no device")
		Msgbox("No USB device or accessory detected!", "Error")
		Log("Msgbox - returned")
End If
Log("Checking permission 1")
	If (usb1.HasPermission(1)) Then	' Ver_2.4
		'Msgbox(usb1.DeviceInfo(1), "Device Information 1")	' Ver_2.4
		Dim dev As Int
		'dev = usb.Open(115200, 1)		' Ver_2.4
		dev = usb1.Open(9600,1)		' Ver_2.4
		If dev <> usb1.USB_NONE Then
			Log("Connected successfully! 1")
					astreams1.Initialize(usb1.GetInputStream, usb1.GetOutputStream, "astreams1")
			flag_inicio_usb=True
		Else
			Log("Error opening USB port 1")
		End If
	Else
		usb1.requestPermission(1)  ' Ver_2.4
	End If
End Sub

Sub Astreams1_NewData (buffer() As Byte)
	Dim msg As String=" "
	Dim b(255) As Byte
	Dim c(50) As Int
	Dim nombre_leer(50) As String
	Log("estamos en buffer 0 "&buffer(0))
	
	
	If buffer(0)=76 Then
		
		If flag_rx=False Then
			Log("LECTURA DE BYTES")
			flag_rx=True
			contador=0
		
		End If
	End If
'	
	If flag_rx=True Then
		'Log(buffer(0))
		contador=contador+1
		
		If contador>224 Then
			'Log("ee"&buffer(0))
			b(contador-225)=buffer(0)
			c(contador-225)=buffer(0)
			'Log(c(contador-225))
			If buffer(0)<> 0 Then
				nombre_leer(contador-225)=Chr(c(contador-225))
				nombre_leido=nombre_leido&Chr(c(contador-225))
				Log(nombre_leido)
			End If
			'log(nombre_leido.Contains("�"))
			
		End If
		
		If contador=255 Then
			ProgressDialogHide
			'Log(msg)
			Log("msg"&	 BytesToString(b,0,b.Length,"UTF8"))
			Log("FIN RX")

			
			flag_rx=False
			If File.Exists(File.DirRootExternal & "/Consola/F1C","Modelo_F1C_"&nombre_leido) Then
				Dim result As Int
				result=Msgbox2("Desea abrir el fichero "&nombre_leido&"?","Abrir Fichero","Si","","No",Null)
				If result=DialogResponse.POSITIVE Then
					ToastMessageShow("Abrimos",True)
					Log(lbModelo.Text)
					If nombre_leido.Contains(lbModelo.Text) Then
						ToastMessageShow("estamos en el",True)
					Else
						'NOMBRE_FICHERO=nombre_leido
						Abrir_Fichero_leido(nombre_leido)
					End If
				End If
				
				If result=DialogResponse.NEGATIVE Then
					ToastMessageShow("Cerramos",True)
				End If
			Else
				Msgbox("No exite el fichero","Error")
				
			End If
		
		End If	
	End If
''	
'	
'	
	If flag_rx=False Then
		If buffer(0)=69 Then
			Leer_fichero
			Log("GRABACION DE BYTES")
		
			astreams1.Write2(buffer_tx,0,191)
			
			astreams1.Write2(buffer_tx,192,64)
			For i=0 To buffer_tx.Length-1
			Log("buffer grab: "&buffer_tx(i))
			Next
		End If
	End If
	If flag_rx=False Then
		If buffer(0)=77 Then
			ProgressDialogHide
			Msgbox("Modelo no corresponde con modalidad","Error")
		End If
		If buffer(0)=70 Then
				Log("FIN GRABACION")
				ProgressDialogHide
		End If
	End If

End Sub

Sub send_data(data As String)
	
	Dim buffer() As Byte 
	'data=data
	buffer = data.GetBytes("UTF8") 
	Log("data "&data)
	Log("Buffer "&buffer(0))
	t=t+1
	Log("contador: "&t)
	astreams1.Write(buffer) 
End Sub

Sub btnClose_Click
	astreams1.Close
	usb1.Close	
'	btnOpen.Enabled = True
'	btnClose.Enabled = False
'	btnSend.Enabled = False
End Sub

Sub AStreams1_Error
	Log("Error: " & LastException)
	
		ProgressDialogHide
		ToastMessageShow("ERROR EN LA LECTURA",True)
	astreams1.Close
End Sub

Sub Astreams1_Terminated
	Log("Terminated")
	astreams1.Close
End Sub


Sub btEscribirMemoria_Click
	flag_rx=False
	    send_data("E")
		  
			
			
			
	
End Sub

Sub Leer_fichero

	'LEEMOS EL FICHERO DONDE SE ENCUENTRAN LOS DATOS A GRABAR
	'lista=File.ReadList(File.DirRootExternal,"Modelo_F1B_miguel  PRUEBA")
	
	For i=0 To lista1.Size-1
	'	Log(lista.Get(i))
    	buffer_tx(i)=lista1.Get(i)
	Next
	
For i=lista1.Size To 255
		buffer_tx(i)=0
	Next
	

			buffer_tx(222)=3
	    	buffer_tx(223)=listaconfiguracion.Get(0)
			Dim aux As String
			Dim b() As Byte
			aux=listaconfiguracion.Get(1)
			b= aux.GetBytes("UTF-8")
			For i=0 To b.Length-1
				buffer_tx(i+224)=b(i)
				
			Next
			
	For i=0 To 255
		Log(buffer_tx(i))
	Next

	Log( "FICHERO DE DATOS LEIDO")

End Sub

Sub enviar_configuracion
'	For i=0 To listaconfiguracion.Size-1
'	'	Log(lista.Get(i))
'    	buffer_tx(i)=listaconfiguracion.Get(i)
'	Next
'	
'	For i=listaconfiguracion.Size To 255
'		buffer_tx(i)=0
'	Next
'	For i=0 To 255
'		Log(buffer_tx(i))
'	Next
'	
'	Log( "FICHERO DE DATOS LEIDO")


End Sub


Sub cbled_CheckedChange(Checked As Boolean)
	If Checked=True Then
		cbled.Text="Led ON"

		listaconfiguracion.Set(0,"1")
	
	Else
		cbled.Text="Led OFF"
		listaconfiguracion.Set(0,"0")
	End If
	
	For i=0 To listaconfiguracion.Size-1 
		Log(listaconfiguracion.Get(i))
	Next
				File.WriteList(File.DirRootExternal & "/Consola/F1C/configuracion","Modelo_F1C_"&NOMBRE_FICHERO,listaconfiguracion)

End Sub


Sub btleer_Click
'	If usb1.UsbPresent(1)=usb1.USB_NONE Then
'
'Log("Msgbox - no device")
'		Msgbox("No USB device or accessory detected!", "Error")
'		Log("Msgbox - returned")
'End If
'Log("Checking permission 1")
'	If (usb1.HasPermission(1)) Then	' Ver_2.4
'		'Msgbox(usb1.DeviceInfo(1), "Device Information 1")	' Ver_2.4
'		Dim dev As Int
'		'dev = usb.Open(115200, 1)		' Ver_2.4
'		dev = usb1.Open(9600,1)		' Ver_2.4
'		If dev <> usb1.USB_NONE Then
'			Log("Connected successfully! 1")
'			'btnOpen.Enabled = False
'			'btnClose.Enabled = True
'			'btnSend.Enabled = True		
			If astreams1.IsInitialized Then
			
			Else
				astreams1.Initialize(usb1.GetInputStream, usb1.GetOutputStream, "astreams1")
			End If
			'valor_d=0
		'	flag_d=False
			'enviar_configuracion
			'Leer_fichero
	'		astreams1.Close
'	astreams1.Initialize(usb1.GetInputStream, usb1.GetOutputStream, "astreams1")
			flag_lectura=True
				flag_rx=False
	    	send_data("L")
			ProgressDialogShow("Leyendo informacion, Por favor espere....")

'		Else
'			Log("Error opening USB port 1")
'		End If
'	Else
'		usb1.requestPermission(1)  ' Ver_2.4
'	End If
nombre_leido=""
End Sub

