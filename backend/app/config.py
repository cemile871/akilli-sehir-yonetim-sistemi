import os
from dotenv import load_dotenv

# Load .env file
load_dotenv()

class Settings:
    DATABASE_URL: str = os.getenv("DATABASE_URL", "postgresql://postgres:postgres@localhost:5432/akillisehir")
    JWT_SECRET: str = os.getenv("JWT_SECRET", "8f45a76e289bf443fde44d2d4d9b23bfae6bb2a9d8bb1a4b49463cb92b512e02")
    JWT_ALGORITHM: str = os.getenv("JWT_ALGORITHM", "HS256")
    ACCESS_TOKEN_EXPIRE_MINUTES: int = int(os.getenv("ACCESS_TOKEN_EXPIRE_MINUTES", "30"))

settings = Settings()
