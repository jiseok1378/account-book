/**
 * node.js 는 그냥 거의 JavaScript하고 비슷하다고 보면 된다.
 * Chrome에 있는 자바스크립트 엔진인 V8을 떼와서 웹 없이 자바스크립트를 사용할 수 있도록 만든거라고 보면 됩니다.
 * 인터프리터 언어입니다. 이론은 이정도만 알아도 충분하지 않을까 싶어서..
 * js 도 class는 있지만 요즘은 잘 안쓰는 추세고 함수형 언어가 이쪽에서 대세다.. 그래서 이쪽으로 알려주도록하겠다.
 * */ 

// python 실행하는것처럼 node [JS 파일 이름] 하면 실행된다. 

// 기본 변수 선언법

var a1 = 1; // var 변수는 안쓴다. 이쪽에 치명적인 버그가 하나 있어서..
let a2 = 2; // var 의 기능을 개선
const a3 = 3; // 불변. 자바의 final과 같음.

console.log(a1, a2, a3);

// var 의 버그 3가지

// 변수 중복 선언 가능
var var1 = 10;
var var1 = 20; // 중복 선언 가능
console.log(var1); // 20 출력 

// 변수 SCOPE가 전역, 함수로 제한

var var2 = 0;
if(true){
    var var2 = 10;
    var name = "IF문 밖에서 내가 나오면 안되는거 알지?"  
}

console.log(var2, name); // var2는 새로 선언한 변수인데 변수 중복 선언에서 다룬것처럼 10으로 재할당돼서 나오고 name 변수는 if 문 밖에서도 참조가 가능하다.


// 변수를 선언하기전에 변수를 사용할 수 있음. (호이스팅 이라고 부름 [둘리인가?])
console.log(var3); // undefined
var var3 = 10
console.log(var3); // 10