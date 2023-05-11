
// 기본적인 함수 선언

function fun1() {
    console.log("I'm fun1!");
}

fun1();

// 함수를 변수에 할당 
let fun2 = function () {
    console.log("I'm fun2!");
}

fun2();

// JS에서 함수는 변수, 파라미터, 리턴값에 모두 사용할 수 있는 1급 시민 객체이다.

// 파라미터에 사용
let paramFun = function (nextJob) {
    console.log("나는 다른 함수를 호출해!");
    nextJob();
}
// 파라미터에 함수 입력
paramFun(fun2);

// 리턴에 사용
let returnFun = function () {
    return function () {
        console.log("나는 리턴된 함수에요")
    }
}
// 리턴받은 함수는 요렇게도 쓸수있고
returnFun()();

let retFun = returnFun();

// 변수에 할당받아서 쓸수도 있다.
retFun();

// 일일이 함수에다 매번 function 쓰기 귀찮으니 화살표로 대체할 수 있다. 이게 익명함수다.

let fun3 = () => {
    console.log("I'm fun3!");
}

fun3();

// 요렇게도 가능 

let fun4 = x => console.log(x)

fun4("I'm fun4!");

// 그래서 화살표 함수를 자주 사용한다.
// 자바는 인터프리터 언어이다 보니까 이전 코드가 끝나기 전에 다음 코드를 실행해버린다. 다음을 보자.

setTimeout(() => console.log("TimeOut..."), 1000) // 1000초의 딜레이 후 TimeOut 출력
console.log("No TimeOut...") // TimeOut 보다 No TimeOut이 먼저 찍히는 경우가 발생한다.

// 이래서 콜백함수를 사용한다.

function callback(fun) {
    setTimeout(() => {
        console.log("TimeOut...")
        fun()
    }, 1000)
}

callback(() => console.log("No TimeOut...")); // 이러면 No timeOut이 TimeOut보다 뒤에 찍힌다.

// 하지만 이렇게 동기를 맞춰주게 되면 콜백 지옥이 발생할 수 있다..

// 콜백 지옥 발생을 위해 1초마다 한번씩 console.log가 3번 찍히게 만들어보자. 
const callback2 = (fun) => {
    setTimeout(() => {
        fun()
    }, 1000);
}

// 이렇게 하면 1초 기다리고 1, 2, 3이 전부 찍혀버리게 된다. 왜냐? 기다리지 않기 때문.
callback2(() => console.log(1))
callback2(() => console.log(2))
callback2(() => console.log(3))

// 콜백 지옥 발생: 꺾쇠모양(>)으로 코드가 휘어지게 된다. 개발자 갈려나가는 소리가 들린다.

callback2(() => {
    console.log(1)
    callback2(() => {
        console.log(2);
        callback2(() => {
            console.log(3);
        })
    })
})


// 콜백지옥 10번 발생
callback2(() => {
    console.log(1)
    callback2(() => {
        console.log(2);
        callback2(() => {
            console.log(3);
            callback2(() => {
                console.log(4);
                callback2(() => {
                    console.log(5);
                    callback2(() => {
                        console.log(6);
                        callback2(() => {
                            console.log(7);
                            callback2(() => {
                                console.log(8);
                                callback2(() => {
                                    console.log(9)
                                    callback2(() => {
                                        console.log(10);
                                    })
                                })
                            })
                        })
                    })
                })
            })
        })
    })
})