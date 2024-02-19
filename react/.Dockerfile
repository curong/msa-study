# 베이스 이미지 설정
FROM node:20-alpine

# 작업 디렉토리 설정
WORKDIR /app

# package.json 및 yarn.lock을 작업 디렉토리로 복사
COPY package.json yarn.lock ./

# yarn 설치
RUN npm install yarn

# 의존성 설치,
RUN yarn install

# 소스 코드를 작업 디렉토리로 복사
COPY . .

# 프로덕션용으로 애플리케이션 빌드
RUN yarn build

# 컨테이너가 실행될 때 실행되는 명령
CMD ["yarn", "dev", "--host", "0.0.0.0"]