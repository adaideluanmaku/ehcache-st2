package com.ch.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Helloword extends Library{
	Helloword instanceDll=(Helloword) Native.loadLibrary("HelloWorld",Helloword.class);
	
}
