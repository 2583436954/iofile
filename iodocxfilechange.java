

import java.io.*;

public class iodocxfilechange  {

    public static void main(String[] args) {
        File f = new File("D:/javaFiles");
        getFilePath(f);
    }
    public static File getFilePath(File f){

        if(f.exists()){

            if(f.isDirectory()){

                File[] files = f.listFiles();
                for (File ff: files) {

                    getFilePath(ff);
                }

            }
            if(f.isFile() && (f.getAbsolutePath().endsWith(".docx")||(f.getAbsolutePath().endsWith(".doc")))){
                copyFileToDir(f);
                System.out.println("f.getAbsolutePah"+f.getAbsolutePath());

            }

        }
        return null;
    }
    public static void copyFileToDir(File f){
        //这里是处理放在哪个地方的问题根据原来的目录文件  让改变一个位置  全部放在c盘 所以 在c盘下慎用
        String path = f.getAbsolutePath();
        path = "E:"+path.substring(path.indexOf(":")+1);

        path = path.substring(0,path.lastIndexOf("\\"));
        System.out.println("path+++++"+path+"绝对路径"+f.getAbsolutePath());
        File ffff = new File(path);
        if(!ffff.exists()){
            ffff.mkdirs();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File fff = new File(path+"/"+f.getName());
        if(fff.exists()){
            System.out.println(fff.getAbsoluteFile()+"这个文件在你要复制的目录下已经存在请删除或重新换个盘");
            System.exit(0);
        }

        try{
            bis = new BufferedInputStream(new FileInputStream(f));
            bos = new BufferedOutputStream(new FileOutputStream(path+"/"+f.getName()));
            byte[] b = new byte[1024*8];
            int len = 0;
            while((len = bis.read(b)) != -1){
                bos.write(b,0,len);
                System.out.println(len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != bis){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

