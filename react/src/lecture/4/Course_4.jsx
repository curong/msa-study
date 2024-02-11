export default function Course_4() {

	/* 변수선언 */
	const a = [1, 2, 3];

	/* 깊은복사 */
	const b = [...a];

	/* 추가하기 */
	b.push(4); // 얕은복사 b의 값이 변한다.
	b.concat(4); // 깊은복사 b의 값이 변하지 않는다.
	const c = [0, ...a, 4]; // 깊은복사 => Spread 연산자를 이용한

	/* 필터 */
	/*
	 * 1. Filter 함수는 Boolean 형식 타입으로 반환.
	 * 2. True 값만 걸러냄.
	 * 3. 필터는 삭제할때 사용.
	 */
	a.filter((n) => n != 1);

	/* 잘라내기 */
	const a4 = [1, 2, 3];
	const b4 = a4.slice(0, 2); //
	const c4 = [a4.slice(0, 2), 4, a4.slice(2, 3)];

	console.log('b4 = >' + b4);
	console.log('c4 => ' + c4);
	console.log(`a4 => ${a4}`);

	/* 반복문 */
	const a5 = [1, 2, 3];

	const b2 = a5.map((n) => n * 2); // Map의 경우 Return 결과가 존재
	a5.forEach((n) => console.log(n)); // Return void.

	const users = [
		{ id: 1, name: '홍길동', phone: "1111" },
		{ id: 2, name: '신용권', phone: "2222" },
		{ id: 3, name: '김철수', phone: "3333" },
		{ id: 4, name: '박길순', phone: "4444" },
	]

	const updateUserDto = { id: 2, name: '정재우', phone: "5555" };

	const result = users.map((user) => user.id === updateUserDto.id ? updateUserDto : user);

	console.log(result)



	return (<></>);
}
