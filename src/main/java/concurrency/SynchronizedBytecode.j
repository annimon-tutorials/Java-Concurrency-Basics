class concurrency.SynchronizedBytecode
    Minor version: 0
    Major version: 52
    Flags: PUBLIC, SUPER
    SourceFile: SynchronizedBytecode.java
    Constant Pool:
            1: MethodReference           #4.#23         //  java/lang/Object.<init>:()V
            2: FieldReference            #3.#24         //  concurrency/SynchronizedBytecode.counter:I
            3: TypeInfo                  #25            //  concurrency/SynchronizedBytecode
            4: TypeInfo                  #26            //  java/lang/Object
            5: Utf8StringConstant        "counter"
            6: Utf8StringConstant        "I"
            7: Utf8StringConstant        "<init>"
            8: Utf8StringConstant        "()V"
            9: Utf8StringConstant        "Code"
           10: Utf8StringConstant        "LineNumberTable"
           11: Utf8StringConstant        "LocalVariableTable"
           12: Utf8StringConstant        "this"
           13: Utf8StringConstant        "Lconcurrency/SynchronizedBytecode;"
           14: Utf8StringConstant        "getCounter"
           15: Utf8StringConstant        "()I"
           16: Utf8StringConstant        "getCounterSynchronizedMethod"
           17: Utf8StringConstant        "getCounterSynchronizedBlock"
           18: Utf8StringConstant        "StackMapTable"
           19: TypeInfo                  #26            //  java/lang/Object
           20: TypeInfo                  #27            //  java/lang/Throwable
           21: Utf8StringConstant        "SourceFile"
           22: Utf8StringConstant        "SynchronizedBytecode.java"
           23: NameAndTypeDescriptor     #7.#8          //  <init>:()V
           24: NameAndTypeDescriptor     #5.#6          //  counter:I
           25: Utf8StringConstant        "concurrency/SynchronizedBytecode"
           26: Utf8StringConstant        "java/lang/Object"
           27: Utf8StringConstant        "java/lang/Throwable"
    
    private static int counter;
        Flags: PRIVATE, STATIC
    
    public void <init>();
        Flags: PUBLIC
        Code:
            stack=1, locals=1, arguments=0
                  linenumber      6
               0: aload_0         /* this */
               1: invokespecial   java/lang/Object.<init>:()V
               4: return         
        LocalVariableTable:
            Start  Length  Slot  Name  Signature
            -----  ------  ----  ----  ----------------------------------
            0      5       0     this  Lconcurrency/SynchronizedBytecode;
    
    public static int getCounter();
        Flags: PUBLIC, STATIC
        Code:
            stack=1, locals=0, arguments=0
                  linenumber      11
               0: getstatic       concurrency/SynchronizedBytecode.counter:I
               3: ireturn        
    
    public static synchronized int getCounterSynchronizedMethod();
        Flags: PUBLIC, STATIC, SYNCHRONIZED
        Code:
            stack=1, locals=0, arguments=0
                  linenumber      15
               0: getstatic       concurrency/SynchronizedBytecode.counter:I
               3: ireturn        
    
    public static int getCounterSynchronizedBlock();
        Flags: PUBLIC, STATIC
        Code:
            stack=2, locals=2, arguments=0
                  linenumber      19
               0: ldc             Lconcurrency/SynchronizedBytecode;.class
               2: dup            
               3: astore_0       
               4: monitorenter   
                  linenumber      20
               5: getstatic       concurrency/SynchronizedBytecode.counter:I
               8: aload_0        
               9: monitorexit    
              10: ireturn        
                  linenumber      21
              11: astore_1       
              12: aload_0        
              13: monitorexit    
              14: aload_1        
              15: athrow         
        Exceptions:
            Try           Handler
            Start  End    Start  End    Type
            -----  -----  -----  -----  ----
            5      10     11     16     Any
            11     14     11     16     Any
