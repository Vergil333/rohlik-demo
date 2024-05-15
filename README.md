###### Create database
`docker run --name rohlik-demo-1 -e POSTGRES_DB=rohlik -e POSTGRES_USER=rohlik -e POSTGRES_PASSWORD=rohlik -p 5434:5432 -d postgres:13`

###### Task questions
- if ID not found while deleting product, throw NotFoundException or process silently?
