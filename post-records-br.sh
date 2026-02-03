#!/bin/bash

URL="http://localhost:8080/planner/record"

services=(
  "Corte cabelo"
  "Fazer unhas"
  "Unhas decor"
  "Manicure"
  "Pedicure"
  "Escova"
)

customers=(
  "Ana Paula"
  "Joao Pedro"
  "Maria Silva"
  "Lucas Alves"
  "Fernanda Lima"
  "Carlos Rocha"
  "Patricia Nunes"
  "Bruno Costa"
)

locations=(
  "Av Brasil"
  "Rua Flores"
  "Rua Central"
  "Av Paulista"
  "Rua Sao Joao"
  "Rua Tiradentes"
)

for i in $(seq 1 30); do
  service=${services[$RANDOM % ${#services[@]}]}
  customer=${customers[$RANDOM % ${#customers[@]}]}
  base_location=${locations[$RANDOM % ${#locations[@]}]}
  number=$((RANDOM % 900 + 1))

  location="$base_location $number"   # still <= 20 chars

  datetime=$(date -u +"%Y-%m-%dT%H:%M:%S.%3NZ")

  curl -s -X POST "$URL" \
    -H "accept: */*" \
    -H "Content-Type: application/json" \
    -d "{
      \"id\": null,
      \"service\": \"$service\",
      \"customer\": \"$customer\",
      \"location\": \"$location\",
      \"dateTime\": \"$datetime\",
      \"done\": false,
      \"canceled\": false
    }"

  echo "✔ $i → $customer | $service | $location"
  sleep 0.1
done
