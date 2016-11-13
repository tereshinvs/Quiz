unit Unit1; 

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, LResources, Forms, Controls, Graphics, Dialogs,
  ExtCtrls, StdCtrls;

type

  { TFormMain }

  TFormMain = class(TForm)
    ButtonNext: TButton;
    ButtonExit: TButton;
    Image1: TImage;
    LabelMark: TLabel;
    RadioGroup1: TRadioGroup;
    procedure ButtonExitClick(Sender: TObject);
    procedure ButtonNextClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure NewTask;
  private
    { private declarations }
  public
    { public declarations }
  end; 
const Number=5;
      Maxn=200;
var FormMain: TFormMain;
    Sum, Mark, RightAnswer, AllKind, ForRandom: longint;
    MyCar, Ident:array [1..Maxn] of String;
    Fin:TextFile;

implementation

{ TFormMain }

procedure TFormMain.FormCreate(Sender: TObject);
begin
     FormMain.BorderStyle:=bsSingle;
     FormMain.Caption:='Машины';
     LabelMark.Caption:='';
     RadioGroup1.Caption:='Выберите верный ответ';
     ButtonNext.Caption:='Следущее задание';
     ButtonExit.Caption:='Выход';
     RadioGroup1.Columns:=Number;
     Mark:=0;
     Sum:=0;

     AssignFile(Fin, 'Data\Answer.txt');
     Reset(Fin);
     AllKind:=0;
     while not eof(Fin) do
     begin
          inc(AllKind);
          readln(Fin, MyCar[AllKind])
     end;
     CloseFile(Fin);

     AssignFile(Fin, 'Data\AllBrand.txt');
     Reset(Fin);
     ForRandom:=0;
     while not eof(Fin) do
     begin
          inc(ForRandom);
          readln(Fin, Ident[ForRandom])
     end;
     CloseFile(Fin);
     NewTask;
end;

procedure TFormMain.ButtonNextClick(Sender: TObject);
var bi:longint;
begin
     if (RadioGroup1.ItemIndex=RightAnswer) then inc(Mark);
     LabelMark.Caption:='Вы верно выполнили '+inttostr(Mark)+' из '+inttostr(Sum);
     for bi:=Number-1 downto 0 do RadioGroup1.Items.Delete(bi);
     NewTask;
end;

procedure TFormMain.ButtonExitClick(Sender: TObject);
begin
     Close;
end;

procedure TFormMain.NewTask;
var ni, nj, x:longint;
    List:array [0..Number-1] of string;
    NewCar, help:string;
    flag:boolean;

begin
     inc(Sum);
     for ni:=0 to Number-1 do List[ni]:='';
     x:=random(AllKind)+1;
     Image1.Picture.LoadFromFile('Data\'+inttostr(x)+'.jpg');
     NewCar:=MyCar[x];
     RightAnswer:=random(Number);
     List[RightAnswer]:=NewCar;
     for ni:=0 to Number-1 do
     begin
          if List[ni]<>'' then continue;
          while true do
          begin
               help:=Ident[random(ForRandom)+1];
               flag:=true;
               for nj:=0 to Number-1 do
                   if List[nj]=help then flag:=false;
               if not flag then continue;
               List[ni]:=help;
               break;
          end;
     end;

     for ni:=0 to Number-1 do RadioGroup1.Items.Add(List[ni]);
end;

initialization
randomize;
  {$I unit1.lrs}

end.

