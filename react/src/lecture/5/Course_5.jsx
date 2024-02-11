import Sub from "@lecture/5/Sub";
import { useState } from "react";

export default function Course_5() {
    let number = 1; // 상태값 아님
    const [number2, setNumber2] = useState(1); // React안에 hooks 라이브러리, 상태값이 됨.


    const numberByAdd = () => {
        number++;
        console.log(`add ${number}`)
    }

    const number2ByAdd = () => {
        setNumber2(number2 + 1); // 리액트한테 number 값 변경 요청
        console.log(number2);
    }

    const [user, setUser] = useState([]); // 레퍼런스가 변경되어야만 동작!!

    const fetchUser = [
        { id: 1, name: '홍길동', age: 20 },
        { id: 2, name: '장보고', age: 21 },
        { id: 3, name: '임꺽정', age: 24 },
        { id: 4, name: '광개토', age: 29 }]

    const handleOnClick = () => {
        setUser([...fetchUser]);

    }


    return (
        <>
            <button onClick={handleOnClick}>다운로드</button>
            <table>
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>이름</td>
                        <td>age</td>
                    </tr>
                </thead>
                <tbody>
                    {user && user.map((user) =>
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.name}</td>
                            <td>{user.age}</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </>
    )
}
