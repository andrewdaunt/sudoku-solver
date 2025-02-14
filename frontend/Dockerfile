ARG NODE_VERSION=23.4.0
FROM node:${NODE_VERSION}-alpine
ENV API_ADDRESS=http://localhost:8080
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install                    
COPY . .                            
RUN npm run build
EXPOSE 5173
CMD ["npm", "run", "dev"]
