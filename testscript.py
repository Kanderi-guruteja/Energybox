import requests
import json

# Base URL of the application
BASE_URL = "http://localhost:8082/api/v1"

# Sample data for testing
sensor_data = {
    "sensorName": "TestSensor",
    "value": 100
}

# Function to create a sensor


def create_sensor(sensor):
    response = requests.post(f"{BASE_URL}/sensors", json=sensor)
    if response.status_code == 201:
        print("Sensor created successfully:", response.json())
        return response.json()["id"]
    else:
        print("Failed to create sensor:", response.text)
        return None

# Function to get all sensors


def get_all_sensors():
    response = requests.get(f"{BASE_URL}/sensors")
    if response.status_code == 200:
        print("All sensors:", response.json())
    else:
        print("Failed to get sensors:", response.text)

# Function to get a sensor by ID


def get_sensor_by_id(sensor_id):
    response = requests.get(f"{BASE_URL}/sensors/{sensor_id}")
    if response.status_code == 200:
        print("Sensor details:", response.json())
    else:
        print(f"Failed to get sensor with ID {sensor_id}:", response.text)

# Function to update a sensor by ID


def update_sensor(sensor_id, new_value):
    updated_data = {
        "value": new_value
    }
    response = requests.put(
        f"{BASE_URL}/sensors/{sensor_id}", json=updated_data)
    if response.status_code == 200:
        print("Sensor updated successfully:", response.json())
    else:
        print(f"Failed to update sensor with ID {sensor_id}:", response.text)

# Function to delete a sensor by ID


def delete_sensor(sensor_id):
    response = requests.delete(f"{BASE_URL}/sensors/{sensor_id}")
    if response.status_code == 204:
        print(f"Sensor with ID {sensor_id} deleted successfully")
    else:
        print(f"Failed to delete sensor with ID {sensor_id}:", response.text)

# Main test function


def main():
    # Create a new sensor
    sensor_id = create_sensor(sensor_data)
    if not sensor_id:
        return

    # Get all sensors
    get_all_sensors()

    # Get sensor by ID
    get_sensor_by_id(sensor_id)

    # Update the sensor
    update_sensor(sensor_id, 200)

    # Get the updated sensor by ID
    get_sensor_by_id(sensor_id)

    # Delete the sensor
    delete_sensor(sensor_id)

    # Verify deletion
    get_all_sensors()


if __name__ == "__main__":
    main()
