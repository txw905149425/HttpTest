package threads;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class WriterFile{
 
    // 指定大小为 100M 的缓冲区 , 一个存缓存数据，一个把满的缓存写入文件
    public static ByteBuffer bytebufferone = ByteBuffer.allocate(102400000);
    public static ByteBuffer bytebuffertwo = ByteBuffer.allocate(102400000);
 
    public static boolean checkbuffer = true;
 
    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < 100000000; i++) {
            if (checkbuffer)
                processone("hello test" + i + "\r\n");
            else
                processtwo("hello test" + i + "\r\n");
        }
        long end = System.nanoTime();
        System.out.println((end - start) + "耗时");
    }
 
    /**
     * bytebuffertwo写日志
     */
    public static void processtwo(String log) {
        // 写bytebuff
        boolean onecheck = checkposition(log, bytebuffertwo);
        if (onecheck)
            writerbuffer(log, bytebuffertwo);
        // 写文件
        else {
            checkbuffer = true;
            writerbuffer(log, bytebufferone);
            writerfile(bytebuffertwo);
        }
    }
 
    /**
     * bytebufferone写日志
     * 
     * @param log
     */
    public static void processone(String log) {
        // 写bytebuff
        boolean onecheck = checkposition(log, bytebufferone);
        if (onecheck) {
            writerbuffer(log, bytebufferone);
        }
        // 写文件
        else {
            checkbuffer = false;
            writerbuffer(log, bytebuffertwo);
            writerfile(bytebufferone);
        }
    }
 
    /**
     * 判断缓存是否可以写下日志
     * 
     * @param log
     * @return
     */
    public static boolean checkposition(String log, ByteBuffer bytebuffer) {
 
        if (2 * log.getBytes().length > bytebuffer.limit() - bytebuffer.position()) {
            return false;
        } else {
            return true;
        }
    }
 
    /**
     * 写日志到缓存，并且返回缓存指针位置
     * 
     * @param log
     * @return
     */
    public static int writerbuffer(String log, ByteBuffer bytebuffer) {
        //for (int i = 0; i < log.length(); i++) {
            //bytebuffer.putChar(log.charAt(i));
            //bytebuffer.put(log.getBytes("UTF-8"));
            bytebuffer.put(log.getBytes());
        //}
        return bytebuffer.position();
    }
 
    /**
     * 写文件
     * 
     * @param filename
     */
    public static void writerfile(ByteBuffer bytebuffer) {
        try {
            FileOutputStream fos = new FileOutputStream(Datefile());
            FileChannel fc = fos.getChannel();
            bytebuffer.flip();
            fc.write(bytebufferone);
            fc.close();
            fos.close();
            bytebuffer.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * 文件名按日期生产
     * 
     * @param str
     * @return
     */
    public static String Datefile() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        //SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HH");
        String str = format.format(new Date());
        return "f:/test/" + str + ".txt";
    }
 
    // 普通的nio读写
    public static void test() {
        try {
            FileOutputStream fos = new FileOutputStream("d:/nio.txt");
            // 得到文件通道
            FileChannel fc = fos.getChannel();
            // 指定大小为 1024 的缓冲区
            ByteBuffer bf = ByteBuffer.allocate(1024);
            // 要写入文件的字符串
            String greeting = "Hello111";
            // 把以上字符串逐字放入缓冲区
            for (int i = 0; i < greeting.length(); i++) {
                bf.putChar(greeting.charAt(i));
            }
            // 记得执行这个方法，使得 position=0, limit=30, 才能写入正确的数据
            // 否则 position 为 30, limit 为 1024，将会把 30 之后的全部空数据(0) 填到文件中
 
            System.out.println(greeting.getBytes().length);
            System.out.println(bf.position());
            System.out.println(bf.limit());
 
            bf.flip();
            // 缓冲区数据写入到文件中，会把缓冲区中从 position 到 limit 之间的数据写入文件
            fc.write(bf);
            fc.close(); // 关闭文件通道
            fos.close(); // 关闭文件输出流
        } catch (Exception e) {
            e.printStackTrace();
 
        }
    }
}