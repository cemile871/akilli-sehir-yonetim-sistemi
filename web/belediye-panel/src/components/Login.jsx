import { useState } from 'react'

export default function Login({ onLoginSuccess }) {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')
    setLoading(true)

    try {
      const response = await fetch('http://127.0.0.1:8000/api/v1/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      })

      const data = await response.json()

      if (response.ok) {
        // Save token and role info to localStorage
        localStorage.setItem('token', data.access_token)
        localStorage.setItem('role', data.role)
        localStorage.setItem('email', email)
        onLoginSuccess(data.role)
      } else {
        setError(data.detail || 'Giriş yapılamadı. Lütfen bilgilerinizi kontrol edin.')
      }
    } catch (err) {
      setError('Bağlantı hatası: Sunucu aktif değil veya erişilemiyor.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div style={{
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      minHeight: '100vh',
      background: '#f4f6f9',
      padding: '20px'
    }}>
      <div className="card" style={{
        width: '100%',
        maxWidth: '400px',
        boxShadow: '0 10px 25px rgba(0, 0, 0, 0.08)',
        padding: '2.5rem 2rem',
        textAlign: 'left'
      }}>
        <div style={{ textAlign: 'center', marginBottom: '2rem' }}>
          <div style={{
            fontSize: '2.5rem',
            marginBottom: '10px',
            display: 'inline-flex',
            alignItems: 'center',
            justifyContent: 'center',
            width: '60px',
            height: '60px',
            background: '#E6F1FB',
            borderRadius: '12px'
          }}>🏛️</div>
          <h2 style={{ fontSize: '18px', fontWeight: '700', color: '#1a1a2e', marginTop: '10px' }}>
            Elazığ Belediyesi
          </h2>
          <p style={{ fontSize: '11px', color: '#64748b', marginTop: '2px' }}>
            Akıllı Şehir Yönetim Sistemi Operatör Girişi
          </p>
        </div>

        {error && (
          <div className="alert alert-r" style={{ marginBottom: '1.25rem' }}>
            <span>⚠️</span>
            <span>{error}</span>
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: '1.25rem' }}>
            <label style={{
              display: 'block',
              fontSize: '11px',
              fontWeight: '600',
              color: '#64748b',
              marginBottom: '6px',
              textTransform: 'uppercase',
              letterSpacing: '0.5px'
            }}>E-POSTA ADRESİ</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              placeholder="admin@belediye.gov"
              style={{
                width: '100%',
                padding: '10px 14px',
                borderRadius: '8px',
                border: '1px solid #e2e8f0',
                fontSize: '13px',
                outline: 'none',
                background: '#f8fafc'
              }}
            />
          </div>

          <div style={{ marginBottom: '1.5rem' }}>
            <label style={{
              display: 'block',
              fontSize: '11px',
              fontWeight: '600',
              color: '#64748b',
              marginBottom: '6px',
              textTransform: 'uppercase',
              letterSpacing: '0.5px'
            }}>ŞİFRE</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              placeholder="••••••••"
              style={{
                width: '100%',
                padding: '10px 14px',
                borderRadius: '8px',
                border: '1px solid #e2e8f0',
                fontSize: '13px',
                outline: 'none',
                background: '#f8fafc'
              }}
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            className="btn btn-primary"
            style={{
              width: '100%',
              justifyContent: 'center',
              padding: '11px',
              fontSize: '13px',
              fontWeight: '600',
              borderRadius: '8px'
            }}
          >
            {loading ? 'Giriş Yapılıyor...' : 'Giriş Yap'}
          </button>
        </form>
      </div>
    </div>
  )
}
