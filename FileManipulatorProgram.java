import java.io.*;
import java.util.stream.Collectors;
import java.nio.file.*;
import java.util.*;

class OperateFile{
    static void reverse(String inputpath, String outputpath){
        if(inputpath.equals(outputpath)){
            System.out.println("インプットパスとアウトプットパスは異なるパスを入力してください。");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(inputpath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputpath));) {
            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(reveresedString(line));
                bw.newLine();
            }
            bw.flush();
            
        } catch (Exception e) {
            System.out.println("ファイルを処理できませんでした。");
        }
    }

    private static final char[] reveresedString(String s){
        char[] cArr = s.toCharArray();
        for(int i = 0; i < cArr.length/2; i ++){
            char temp = cArr[i];
            cArr[i] = cArr[cArr.length-i-1];
            cArr[cArr.length-i-1] = temp;
        }
        return cArr;
    }

    static void copy(String inputpath, String outputpath){
        if(inputpath.equals(outputpath)){
            System.out.println("インプットパスとアウトプットパスは異なるパスを入力してください。");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(inputpath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputpath));) {
            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            
        } catch (Exception e) {
            System.out.println("ファイルをコピーできませんでした。");
        }
    }
    
    static void duplicate(String inputpath, int n){
        /*方針
         * inputpathのコンテンツを読み取りListに保存する。新規に作成したファイルinputpath+copyにListの各要素をｎ回書き込む。
         * 書き込み完了後、inputpathを削除する。その後、新規作成したファイル名をinputoathに変更する。
         */
        File inputFile = new File(inputpath);
        File outputFile = new File(inputpath + "-copy");
        try (
             BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true));) 
            {
                //コンテンツを一行ごとに、リストの要素として格納する。
                List<String> getContents = br.lines().collect(Collectors.toList());
                for(int i = 0; i < n; i ++){
                    for(String s : getContents){
                        bw.write(s);
                        bw.newLine();
                    }
                }
                bw.flush();
        } catch (Exception e) {
            System.out.println("ファイルを処理できませんでした。");
        }
        //ファイルの操作とファイル名の変更
        if(!inputFile.delete()){ 
            System.out.println("ファイル操作に失敗しました。");
            return;
        }
        if(!outputFile.renameTo(inputFile)){
            System.out.println("ファイル操作に失敗しました。");
            return;
        }
    }
    
    static void replaceString(String inputpath, String target, String replacedWord){
        /*方針
         * inputpathのコンテンツを読み取る。新規のファイルを作成する。
         * コンテンツにtargetがあればreplacedWorfに置換する。
         * 置換後のコンテンツを新規ファイルに書き込む。
         * 書き込み完了後、inputpathを削除し、新規ファイルの名称をinputpathに変更する。
         */
        File inputFile = new File(inputpath);
        File outputFile = new File(inputpath + "-copy");
        try (
             BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));) 
             {
                String line = null;
                while ((line = br.readLine()) != null) {
                    bw.write(line.replaceAll(target, replacedWord));
                    bw.newLine();
                }
                bw.flush();
        } catch (Exception e) {
            System.out.println("ファイルをコピーできませんでした。");
        }
        //ファイルの操作とファイル名の変更
        if(!inputFile.delete()){ 
            System.out.println("ファイル操作に失敗しました。");
            return;
        }
        if(!outputFile.renameTo(inputFile)){
            System.out.println("ファイル操作に失敗しました。");
            return;
        }
    }
    
}

public class FileManipulatorProgram {
    static void printError(){System.out.println("コマンドライン引数が誤っている可能性があります。");}

    public static void main(String[] args) {
        //reverse, copy, duplicate-contents, replace-stringのいづれかの処理をする。
        
        String command = args[0];
        switch (command) {
            case "reverse" -> {
                if(args.length != 3){printError();}
                else OperateFile.reverse(args[1], args[2]);
            }
            case "copy" ->{
                if(args.length != 3){printError();}
                else OperateFile.copy(args[1], args[2]);
            }
            case "duplicate-contents" ->{
                if(args.length != 3 || !args[2].matches("[0-9]{0,}")){printError();}
                else OperateFile.duplicate(args[1], Integer.parseInt(args[2]));
            }
            case "replace-string" ->{
                if(args.length != 4){printError();}
                else OperateFile.replaceString(args[1], args[2], args[3]);
            }
            default -> printError();
        }
    }
}
