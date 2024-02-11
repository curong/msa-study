import { Routes, Route } from 'react-router-dom';
import Course_4 from '@lecture/4/Course_4';
import Course_5 from '@lecture/5/Course_5';


export default function LectureRoutes() {
	return (
		<Routes>
			<Route path="/lec/4" Component={Course_4}></Route>
			<Route path="/lec/5" Component={Course_5}></Route>
		</Routes>
	);
}
