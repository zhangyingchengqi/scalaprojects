import java.util.Date


object Test3 extends App {
  ///////////////////1变量的声明
  //var:变量       val:类似于 java  final修改  
  //  语法:    var|val  变量名(:类型)=值
  var age=20
  var age2:Int=20
  
  age=22
  age2=52
  
  val name="zy"
  val name2:String="张三"
  
  //name="lisi"
  
  ///////////////////2. 输出语句
  println(   "姓名:"+name+",年龄:"+age )
  //   f 插值符允许创建一个格式化字符串      类似于 c中的  printf(   )    格式字符  %d   %f等
  //另外使用   $变量名引变量的值
  println(f"姓名:$name%s,年龄:$age%d")
  //更象c一些
  printf("姓名:%s,年龄:%d\n",name,age)
  println(f"姓名:$name%s,年龄:${age+1}%d")         //  插值符可以支持运算
  
  ///////////////////3。 if 表达式   获取它的结果
  val salary=50000
  var res1=if( salary>10000  ) "youxi" else "养不活"
  println(  res1 )
  
  var res2=if( salary>100000  ) "youxi" 
  println(  res2 )    //   ()    表示 null  
  
  var score=70
  val res4={
    if(  score<60 ) "不及格"
    else if( score>60 && score <70) "及格"
    else if(score>70 && score<90) "良好"
    else "优秀"
  }
  println(  res4) 
  
   ///////////////////4。 循环
  //    while  for
  // for 循环  :    for( 变量<- 表达式/数组/集合 )
  val array=Array( 1,2,3,4,5,6    )
  for( ele <- array ){
     println(  ele )
  }
  //     x to y  生成一个从   x到 y 的一个集合
  for( i <- 0 to 5 ){
    println(   array(i) )     // []   ->    ()   方法 
  }
  //   x until y: 生成  x到 y-1的一个集合
  for(  i<- 0 until 5 ){
    println(  array(i) )
  }
  println("========")
  //  加入条件判断
  for( ele<-array if ele%2==0){
    println(   ele   )
  }
  println("========")
  for( i<- 1 to 3; j<- 1 to 3 if i!=j ){
    println(   (10*i+j)+"" )
  }
  println("========")
   //  yield     :   线程  :  Thead.yield
  val res5=for(  e<-array if e%2==0) yield e      //   将前面  for循环中满足条件的值取出来，存到一个集合中
  println(  res5 )
  for(  i<- res5 ){
    println(   i   )
  }
  for(  i<- res5 ){
    println(   i   )
  }
  
   ///////////////////5. 运算符重载    c++  
   println("========")
  var a=1
  var b=2
  println(   a+b  )
  println(  a.+(b) ) 
  
  
  ///////////////////6. 函数
  //   语法:   def 函数名(   [参数列表]    ):[return type] ={    }
  //    [参数列表]:        参数名:类型,参数名1:类型1
   println("========")
  def sum(a:Int,b:Int):Int={
      a+b
   }
   var result=sum(1,2)
   println( result )
   
   def fac(  n:Int )={
     var r=1
     for(  i<- 1 to n) r=r*i
     r
   }
   println(   fac(3) )
   //1. 指定默认值
   def decorate(  str: String, left:String="[", right:String="]" ) ={
     left+str+right
   }
   println(   decorate(   "hello world" )  )
   println(   decorate( "hello world","^","&" ) )
   println(   decorate( "hello world",right="&" ) )    //可以使用命名参数来指定对应的参数
   //2.   变长参数        java: doUpdate(  String...   params )
   def sum(  args:Int* )={
     var result=0
     for( i<- args){
       result+=i
     }
     result
   }
   println(    sum(1,2,3,4,5) )
   //3. 定义一个函数，这个函数没有参数，也没有返回值
   def sayHello=println("hello world")
   sayHello
   //4. 过程: 是一种没有返回值的特殊函数
   def box( s:String){    // abc   3   
     var border="-"*(s.length*2)   //  6个-
     println(  f"$border%n$s%n$border%n"  ) 
   }
   box(   "hello" ) 
   //5。 匿名函数的声明与调用
   val  f1=(x:Int)=>x*10
   println(  f1(10)  )
   val f2:(Int,Int)=>Int=(x,y) => x*y
   println(  f2(2,3 ) )
   val f3:()=>Int=()=> 1
   println(   f3() )
   
   //匿名函数的应用
   def currentTime():Long={
     println("系统时间为:")
     System.nanoTime()
   }
   println(   currentTime() )
   
   def delayed(  f: => Long ):Unit={  //在这里这个f就是一个回调函数
     println("输出====")
     println(   "time="+f )
   }
   delayed(     currentTime   )
   
   //****高阶函数:   python:   hadoop     max(        )   min(     )    avg(       )   sorted(  xxx  )
   //将其他函数作为参数或结果是函数的函数
   //定义一个方法:  参数带一个整型参数返回值为整型的函数f和一个整型参数v,返回值也是一个函数
   def  apply(   f:Int=>String, v:Int) =f(v)     //主函数
   
   //回调函数
   def layout(   x:Int) =   "["+  ( x+10 ) +"]"
   
   println(   apply( layout, 10 ) )
   
   //*****部分参数应用函数  :有些函数有多个参数，如调用时，全部传入，则这个函数被完全调用. 
   //   特殊情况，如果只传部分参数，那么将返回部分应用的函数,这里，已传入的参数会被记录,以供后面多次调用
   def log(   data:Date,  message:String)={
     println(  s"$data, $message")
   }
   //日志有多条，要求时间一样
   val date=new Date()
   
   val logPartial:(String)=>Unit=log( date  , _:String) 
   
   logPartial(  "hello" )
   logPartial(  "world" )
   logPartial(  "bye" )
   //保存已有值，防止干扰. 
   
   //柯里化:  currying  
   //将原来接收两个参数的函数变成接受一个参数的函数. 
   def add( x:Int, y:Int)=x+y
   //柯里化
   def add1(x:Int)(y:Int)=x+y
   
   //分析一下  :   def add1(x:Int) = (y:Int)=>x+y     理解: (y:Int)=>x+y 是一个匿名函数，
   val ff1=add1(2)(1)   // =>    result就是    (y:Int)=>2+y 
   println(   ff1  )
   
   
   /////数组
   var x:Array[String]=new Array[String](3)
   
   var y=new Array[String](3)
   
   var z=Array(1,2,3)
   
   println(  z(0)  ) 
   
   //  集合的高阶:  map|flatten | flatMap|   |foreach
   val array1=Array[Int](2,4,6,8,10)
   // 1. map:将 array1每个元素提取应用到一个匿名函数上
   val re1=array1.map(       (x:Int)=>x+1       )
   println(  re1.toList )
    for( i<-re1){
        println(   i  )
    }
   
    array1.foreach( println )   
    /*
     *    def foreach(  f1 ){
     *         for( i<-this){
     *            println( i )
     *         }
     *    }
     */
   
   //map
   val words=Array("hello world tom come from china","hello mao")
   val splitwords=words.map(    wd => wd.split(" ") )
   splitwords.foreach(  println )
   
   val flattens=splitwords.flatten  //将多维数组展平为一维
   println(  flattens )
    flattens.foreach(  println  ) 
    
    //以上两步  map  ,flatten,可以合并成一步  flatmap
   val rr= words.flatMap(   wd=> wd.split(" " ) )
   rr.foreach(   println )
   
   
   
   
   
   
   
   
   
   
   
   
   
   
  
  
  
  
  
  
  
  
  

  
  
  
}