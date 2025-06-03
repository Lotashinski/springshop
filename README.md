# springshop
Simple spring project (without springboot)

## application capabilities 
- Managing product lists
- Grouping products by categories
- Working with the customer's shopping cart
- Compiling orders

## setup
```bash
git clone https://github.com/Lotashinski/springshop
cd springshop
```
Create ``.env`` file (example ``.env.example``) 

Then you can build project and run docker

```bash
mvn clean package
docker compose up -d
```