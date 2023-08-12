# hotel_reg
Simple hotel registration, without the use of springboot.


execute db:::

docker run --name hotel -e POSTGRES_PASSWORD=root -p 5432:5432 -v /Users/karolisvalatka/IdeaProjects/hotel_reg/src/main/resources:/docker-entrypoint-initdb.d -d postgres

