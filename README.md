# FileManipulatorProgram

## 使い方
- コマンドライン引数に所定の引数を渡し、ファイルを操作することができます。

### reverse
- inputpathにあるファイルを受け取り、outputpathにinputpathの内容を逆にした新しいファイルを作成します。
- args[0] == "reverse" 
- args[1] == {inputpath}
- args[2] == {outputpath}

``````
$ java FileManipulatorProgram reverse inputpath outputpath
``````

### copy
- inputpathにあるファイルのコピーを作成し、outputpathとして保存します。
- args[0] == "copy" 
- args[1] == {inputpath}
- args[2] == {outputpath}

``````
$ java FileManipulatorProgram copy inputpath outputpath
``````

### duplicate-contents
- inputpathにあるファイルの内容を読み込み、その内容を複製し、複製された内容をinutpathにn回複製します。
- args[0] == "duplicate-contents"
- args[1] == {inputpath}
- args[2] == {n}
- 以下は、3回複製する場合のコマンドの例。

``````
$ java FileManipulatorProgram duplicate-contents inputpath 3
``````

### replace-string
- inputpathにあるファイル内容から文字列'originalword'を検索し、'originalword'の全てを'replacedword'に置き換えます。
- args[0] == "replace-string"
- args[1] == {inputpath}
- args[2] == {'originalword'}
- args[3] == {'replacedword'}
- 以下は、ファイル内容に'day'が含まれる場合、'day'を'Day'に置き換える場合のコマンドの例。

``````
$ java FileManipulatorProgram replace-string inputpath day Day
``````