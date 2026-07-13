package com.amit.demo;

class P {
    void n1(){}
    void n2(){}
}

class C1 extends P{
    void n3(){}

}

class Test {
    C1 c1 = new C1();
//    C1 c2 = new P();  // cannot create this cause p only have 2 methods details
}



