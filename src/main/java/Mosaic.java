

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Mosaic {
    BufferedImage bi = null;
    static int [][] matrix = null;
    
    static final int COLOR_THRESHOLD = 100;
    public static int[][] transposedMatrix(int [][] m){
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
    
    public static int[][] transformToMatrix(File file) throws Exception {
        
        int size = 1;
      
        BufferedImage bi = ImageIO.read(file); // 读取该图片
        int xcount = 0; // 方向绘制个数
        int ycount = 0; // y方向绘制个数
        if (bi.getWidth() % size == 0) {
            xcount = bi.getWidth() / size;
        } else {
            xcount = bi.getWidth() / size + 1;
        }
        if (bi.getHeight() % size == 0) {
            ycount = bi.getHeight() / size;
        } else {
            ycount = bi.getHeight() / size + 1;
        }
        int x = 0;   //坐标
        int y = 0;
        matrix = new int[xcount][ycount];
        // 绘制马赛克(绘制矩形并填充颜色)
        System.out.println(xcount);
        System.out.println(ycount);

        for (int i = 0; i < xcount; i++) {
            for (int j = 0; j < ycount; j++) {
                //马赛克矩形格大小
                 int mwidth = size;
                 int mheight = size;
                 if(i==xcount-1){   //横向最后一个比较特殊，可能不够一个size
                     mwidth = bi.getWidth()-x;
                 }
                 if(j == ycount-1){  //同理
                     mheight =bi.getHeight()-y;
                 }
              // 矩形颜色取中心像素点RGB值
                int centerX = x;
                int centerY = y;
                if (mwidth % 2 == 0) {
                    centerX += mwidth / 2;
                } else {
                    centerX += (mwidth - 1) / 2;
                }
                if (mheight % 2 == 0) {
                    centerY += mheight / 2;
                } else {
                    centerY += (mheight - 1) / 2;
                }
                Color color = new Color(bi.getRGB(centerX, centerY));
                int averageColor = (color.getGreen()+color.getRed()+color.getRed())/3;
                if(averageColor<COLOR_THRESHOLD)
                		matrix[i][j] = 0;
                if(averageColor<COLOR_THRESHOLD)
            			matrix[i][j] = 1;
                y = y + size;// 计算下一个矩形的y坐标
            }
            y = 0;// 还原y坐标
            x = x + size;// 计算x坐标
        }
        
      return transposedMatrix(matrix);
        
    }
}

    



