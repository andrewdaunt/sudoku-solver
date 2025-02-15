ARG NODE_VERSION=23.4.0
FROM node:${NODE_VERSION}-alpine
ARG VITE_API_ADDRESS
ENV VITE_API_ADDRESS=${VITE_API_ADDRESS}
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install                    
COPY . .                            
RUN npm run build
EXPOSE 5173
CMD ["npm", "run", "dev"]
