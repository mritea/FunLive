package com.github.yglll.funlive.danmu.utils;

/**
 * 作者：YGL
 * 版本号：1.0
 * 类描述：
 * 备注消息：此部分代码参考自项目:https://github.com/Bilibili/DanmakuFlameMaster
 * 创建时间：2018/02/17
 **/
public class FormatTransfer {
    public   static   byte [] toLH( int  n)
    {
        byte [] b =  new   byte [ 4 ];
        b[0 ] = ( byte ) (n &  0xff );
        b[1 ] = ( byte ) (n >>  8  &  0xff );
        b[2 ] = ( byte ) (n >>  16  &  0xff );
        b[3 ] = ( byte ) (n >>  24  &  0xff );
        return  b;
    }

    public   static   byte [] toHH( int  n)
    {
        byte [] b =  new   byte [ 4 ];
        b[3 ] = ( byte ) (n &  0xff );
        b[2 ] = ( byte ) (n >>  8  &  0xff );
        b[1 ] = ( byte ) (n >>  16  &  0xff );
        b[0 ] = ( byte ) (n >>  24  &  0xff );
        return  b;
    }

    public   static   byte [] toLH( short  n)
    {
        byte [] b =  new   byte [ 2 ];
        b[0 ] = ( byte ) (n &  0xff );
        b[1 ] = ( byte ) (n >>  8  &  0xff );
        return  b;
    }

    public   static   byte [] toHH( short  n)
    {
        byte [] b =  new   byte [ 2 ];
        b[1 ] = ( byte ) (n &  0xff );
        b[0 ] = ( byte ) (n >>  8  &  0xff );
        return  b;
    }
}
