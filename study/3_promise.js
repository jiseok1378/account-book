// 앞서 2에서 설명했던 콜백지옥을 어떻게 해결할까 하다 나온 방법이 Promise 이다. 콜백함수를 좀 더 간편히 사용할 수 있는 함수이다.
// 1~3까지 1초에 한번씩 출력하는 예제 Promise를 이용해서 변경해보자.

function timeout(callback) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            callback()
            resolve()
        }, 1000)
    })
}


// 사실 promise를 사용해도 이와 같은 상황에서 별 다를게 없다. 마찬가지로 코드가 오른쪽으로 휘어진다.
timeout(() => console.log(1))
    .then(() => timeout(() => console.log(2))
        .then(() => timeout(() => console.log(3))))

// 이 문제를 해결하기 위해 다음과 같이 async, await를 사용해서 간편하게 사용할 수 있다.

const asyncFun = async () => {
    await timeout(() => console.log(1));
    await timeout(() => console.log(2));
    await timeout(() => console.log(3));
}
asyncFun()