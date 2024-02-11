import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
	plugins: [react()],
	resolve: {
		alias: {
			'@components': '/src/components',
			'@pages': '/src/pages',
			'@images': '/src/assets/images',
			'@stylesheets': '/src/assets/stylesheets',
			'@routes': '/src/routes',
			'@lecture': '/src/lecture',
			'@/': '/src/*',
		},
	},
});
