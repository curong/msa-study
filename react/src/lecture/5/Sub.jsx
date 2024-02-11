import { useState } from 'react';

export default function Sub() {
    const [number2, setNumber2] = useState(1);

    const number2ByAdd = () => {
        setNumber2(number2 + 1);
        console.log(number2);
    }

    return (
        <div>
            <div>
                숫자 : {number2}
            </div>
            <button onClick={number2ByAdd}>+</button>
        </div>
    )
}
