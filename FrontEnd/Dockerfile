FROM node:latest
RUN npm install -g @angular/cli@15.2.6
RUN mkdir /code
WORKDIR /code
COPY package.json /code/
ADD . /code/
RUN npm install
RUN npm update -D
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200", "--configuration", "development"]
