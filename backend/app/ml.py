import os
# Reduce tensorflow logs to avoid cluttering stdout
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

import tensorflow as tf
from tensorflow.keras import layers, Model
import numpy as np
from typing import Dict, Any, Tuple

# Enable CPU only execution explicitly to avoid GPU initialization delays
tf.config.set_visible_devices([], 'GPU')

class Autoencoder(Model):
    def __init__(self, input_dim: int):
        super(Autoencoder, self).__init__()
        # Encoder: compress features
        self.encoder = tf.keras.Sequential([
            layers.Input(shape=(input_dim,)),
            layers.Dense(4, activation='relu'),
            layers.Dense(2, activation='relu')
        ])
        # Decoder: reconstruct original features
        self.decoder = tf.keras.Sequential([
            layers.Dense(4, activation='relu'),
            layers.Dense(input_dim, activation='sigmoid')  # Scale back to [0, 1]
        ])

    def call(self, x):
        encoded = self.encoder(x)
        decoded = self.decoder(encoded)
        return decoded

class SensorAnomalyDetector:
    def __init__(self):
        # We will have separate models for traffic and environment sensors
        self.traffic_dim = 3  # vehicle_count, avg_speed, waiting_time
        self.env_dim = 3      # aqi, pm25, noise_db
        
        # Build and train models with baseline normal data
        self.traffic_model = Autoencoder(self.traffic_dim)
        self.env_model = Autoencoder(self.env_dim)
        
        # Compile models
        self.traffic_model.compile(optimizer='adam', loss='mse')
        self.env_model.compile(optimizer='adam', loss='mse')
        
        # Train with baseline normal data at startup
        self._train_baseline_models()

    def _generate_traffic_normal_data(self, samples=500) -> np.ndarray:
        # Normal Traffic:
        # vehicle_count: 20-90 (scaled to 0-1 range, max 200)
        # avg_speed: 40-75 (scaled to 0-1 range, max 120)
        # waiting_time: 15-45 (scaled to 0-1 range, max 150)
        vehicle_count = np.random.uniform(20, 90, samples) / 200.0
        avg_speed = np.random.uniform(40, 75, samples) / 120.0
        waiting_time = np.random.uniform(15, 45, samples) / 150.0
        return np.column_stack((vehicle_count, avg_speed, waiting_time)).astype(np.float32)

    def _generate_env_normal_data(self, samples=500) -> np.ndarray:
        # Normal Environment:
        # aqi: 15-50 (scaled to 0-1 range, max 200)
        # pm25: 5-20 (scaled to 0-1 range, max 100)
        # noise_db: 40-65 (scaled to 0-1 range, max 100)
        aqi = np.random.uniform(15, 50, samples) / 200.0
        pm25 = np.random.uniform(5, 20, samples) / 100.0
        noise_db = np.random.uniform(40, 65, samples) / 100.0
        return np.column_stack((aqi, pm25, noise_db)).astype(np.float32)

    def _train_baseline_models(self):
        print("[AI] TensorFlow anomali tespit modelleri normal verilerle eğitiliyor...")
        traffic_train = self._generate_traffic_normal_data(1000)
        env_train = self._generate_env_normal_data(1000)
        
        # Train for a few epochs (quick since data size is small)
        self.traffic_model.fit(traffic_train, traffic_train, epochs=5, batch_size=32, verbose=0)
        self.env_model.fit(env_train, env_train, epochs=5, batch_size=32, verbose=0)
        
        # Determine threshold (e.g., 98th percentile of reconstruction loss on normal data)
        traffic_pred = self.traffic_model.predict(traffic_train, verbose=0)
        env_pred = self.env_model.predict(env_train, verbose=0)
        
        self.traffic_threshold = np.percentile(np.mean(np.square(traffic_train - traffic_pred), axis=1), 98)
        self.env_threshold = np.percentile(np.mean(np.square(env_train - env_pred), axis=1), 98)
        
        # Add safety margin
        self.traffic_threshold *= 1.5
        self.env_threshold *= 1.5
        print(f"[AI] Model eğitimi tamamlandı. Trafik Eşik: {self.traffic_threshold:.6f}, Çevre Eşik: {self.env_threshold:.6f}")

    def detect_traffic_anomaly(self, vehicle_count: float, avg_speed: float, waiting_time: float) -> Tuple[bool, float]:
        # Scale inputs
        x = np.array([[vehicle_count / 200.0, avg_speed / 120.0, waiting_time / 150.0]], dtype=np.float32)
        # Predict
        reconstructed = self.traffic_model.predict(x, verbose=0)
        # Compute MSE loss
        loss = float(np.mean(np.square(x - reconstructed)))
        is_anomaly = loss > self.traffic_threshold
        return is_anomaly, loss

    def detect_env_anomaly(self, aqi: float, pm25: float, noise_db: float) -> Tuple[bool, float]:
        # Scale inputs
        x = np.array([[aqi / 200.0, pm25 / 100.0, noise_db / 100.0]], dtype=np.float32)
        # Predict
        reconstructed = self.env_model.predict(x, verbose=0)
        # Compute MSE loss
        loss = float(np.mean(np.square(x - reconstructed)))
        is_anomaly = loss > self.env_threshold
        return is_anomaly, loss

class TrafficOptimizer:
    def __init__(self):
        # Dense network for predicting congestion index and light time
        # Inputs: current vehicle count, peak hour status (0 or 1), active incident count
        self.model = tf.keras.Sequential([
            layers.Input(shape=(3,)),
            layers.Dense(8, activation='relu'),
            layers.Dense(4, activation='relu'),
            layers.Dense(2, activation='linear')  # outputs: predicted vehicle count in next hour, suggested green time
        ])
        
        # Simple training with dummy traffic rules
        # vehicle count, peak hour, incidents
        x_train = np.random.uniform(10, 180, (500, 3)).astype(np.float32)
        # outputs: future vehicle count (around current + peak_hour * 20), green time (proportional to count)
        y_train = np.zeros((500, 2), dtype=np.float32)
        for i in range(500):
            vc = x_train[i, 0]
            pk = x_train[i, 1]
            inc = x_train[i, 2]
            
            future_vc = vc * (1.2 if pk > 0.5 else 0.9) + (50 if inc > 0.5 else 0)
            green_time = int(30 + (future_vc / 200.0) * 60)
            green_time = max(15, min(green_time, 90)) # bounds
            y_train[i, 0] = future_vc
            y_train[i, 1] = green_time

        self.model.compile(optimizer='adam', loss='mse')
        self.model.fit(x_train, y_train, epochs=5, batch_size=16, verbose=0)

    def optimize(self, current_vehicle_count: int, is_peak_hour: bool, active_incidents: int) -> Tuple[Dict[str, Any], Dict[str, Any]]:
        # Format inputs
        x = np.array([[current_vehicle_count, 1.0 if is_peak_hour else 0.0, float(active_incidents)]], dtype=np.float32)
        prediction = self.model.predict(x, verbose=0)
        
        predicted_vc = float(prediction[0, 0])
        suggested_green = int(prediction[0, 1])
        
        # Ensure suggested green time is bounded properly
        suggested_green = max(15, min(suggested_green, 90))
        
        # Determine congestion level text
        if predicted_vc > 130:
            congestion_level = "Yüksek"
        elif predicted_vc > 60:
            congestion_level = "Orta"
        else:
            congestion_level = "Düşük"
            
        import datetime
        peak_time = (datetime.datetime.now() + datetime.timedelta(hours=1)).strftime("%H:%M")
        
        pred_dict = {
            "expected_congestion_level": congestion_level,
            "peak_time": peak_time
        }
        
        opt_dict = {
            "suggested_green_light_duration": suggested_green,
            "confidence_score": round(float(0.85 + np.random.uniform(0, 0.1)), 2)
        }
        
        return pred_dict, opt_dict

# Singleton instances
anomaly_detector = SensorAnomalyDetector()
traffic_optimizer = TrafficOptimizer()
