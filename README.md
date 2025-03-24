# Load API Project

## Overview

The Load API is designed to facilitate the creation of load records with essential transportation details such as loading and unloading points, dates, product type, truck specifications, and shipper information.

## API Endpoint

```bash
POST /load
```

### Base URL

```bash
localhost:8080
```

## Request Headers

```json
{
  "Content-Type": "application/json"
}
```

## Request Body

```json
{
  "facility": {
    "loadingPoint": "Delhi",
    "unloadingPoint": "Jaipur",
    "loadingDate": "2025-03-20",
    "unloadingDate": "2025-03-21"
  },
  "productType": "Chemicals",
  "truckType": "Canter",
  "noOfTrucks": 1,
  "weight": 100.0,
  "comment": "",
  "shipperId": "550e8400-e29b-41d4-a716-446655440000",
  "date": "2025-03-20"
}
```

## Field Descriptions

- **facility**: Contains loading and unloading details.
  - **loadingPoint**: Starting location (e.g., Delhi).
  - **unloadingPoint**: Destination location (e.g., Jaipur).
  - **loadingDate**: Date when loading starts (YYYY-MM-DD).
  - **unloadingDate**: Date when unloading ends (YYYY-MM-DD).
- **productType**: Type of product being transported (e.g., Chemicals).
- **truckType**: Type of truck used (e.g., Canter).
- **noOfTrucks**: Number of trucks required.
- **weight**: Total weight of the load in kilograms.
- **comment**: Additional comments or notes.
- **shipperId**: Unique identifier for the shipper.
- **date**: Date when the load record is created (YYYY-MM-DD).

## Example cURL Command

```bash
curl --location 'localhost:8080/load' \
--header 'Content-Type: application/json' \
--data '{
  "facility": {
    "loadingPoint": "Delhi",
    "unloadingPoint": "Jaipur",
    "loadingDate": "2025-03-20",
    "unloadingDate": "2025-03-21"
  },
  "productType": "Chemicals",
  "truckType": "Canter",
  "noOfTrucks": 1,
  "weight": 100.0,
  "comment": "",
  "shipperId": "550e8400-e29b-41d4-a716-446655440000",
  "date": "2025-03-20"
}'
```

## Response

The API will respond with a status code and a message indicating success or failure.

## Error Handling

- **400 Bad Request**: Missing or invalid fields.
- **500 Internal Server Error**: Server encountered an unexpected condition.

## Notes

- Ensure all date fields follow the `YYYY-MM-DD` format.
- `shipperId` must be a valid UUID.
- The weight should be a positive numeric value.

## Contact

For issues or support, contact the development team at [[support@example.com](mailto\:support@example.com)].

