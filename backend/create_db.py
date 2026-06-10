import psycopg2
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT

try:
    # Connect to the default 'postgres' database
    conn = psycopg2.connect(
        dbname="postgres",
        user="postgres",
        password="123",
        host="127.0.0.1",
        port="5432"
    )
    conn.set_isolation_level(ISOLATION_LEVEL_AUTOCOMMIT)
    cursor = conn.cursor()
    
    # Check if database exists
    cursor.execute("SELECT 1 FROM pg_catalog.pg_database WHERE datname = 'akillisehir';")
    exists = cursor.fetchone()
    
    if not exists:
        print("[DB SETUP] Creating database 'akillisehir'...")
        cursor.execute("CREATE DATABASE akillisehir;")
        print("[DB SETUP] Database 'akillisehir' successfully created!")
    else:
        print("[DB SETUP] Database 'akillisehir' already exists.")
        
    cursor.close()
    conn.close()
except Exception as e:
    print(f"[DB SETUP] Error creating database: {e}")
