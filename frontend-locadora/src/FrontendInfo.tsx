import React from 'react';
// 1. Importe o tipo CSSProperties do React
import type { CSSProperties } from 'react';

// 2. Use 'CSSProperties' para tipar o objeto de estilo
const bannerStyle: CSSProperties = {
  backgroundColor: '#282c34',
  color: 'white',
  textAlign: 'center', // Agora o TypeScript sabe que 'center' é um valor válido
  padding: '10px 0',
  fontWeight: 'bold',
  fontFamily: 'Arial, sans-serif',
  fontSize: '0.9em'
};

function FrontendInfo() {
  const instanceId = import.meta.env.VITE_INSTANCE_ID;

  return (
    <div style={bannerStyle}>
      Conectado ao: Frontend - {instanceId}
    </div>
  );
}

export default FrontendInfo;