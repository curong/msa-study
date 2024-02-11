import { Routes, Route, BrowserRouter } from 'react-router-dom';
import LectureRoutes from '@routes/LectureRoutes';
import IndexPage from '@pages/IndexPage';

export default function RootRoutes() {
	return (
		<BrowserRouter>

			{/* 공통 라우터 */}
			<Routes>
				<Route path="/" Component={IndexPage}></Route>
			</Routes>

			{/* 강의 Route */}
			<LectureRoutes />

		</BrowserRouter>
	);
}
