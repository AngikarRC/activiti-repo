const BASE_URL = 'http://localhost:9090';

async function apiRequest(path, method = 'GET', body = null) {
  const headers = { 'Content-Type': 'application/json' };
  const options = { method, headers };
  if (body) options.body = JSON.stringify(body);

  const res = await fetch(BASE_URL + path, options);
  if (!res.ok) throw new Error(`Error: ${res.status}`);
  return await res.text(); // or .json() depending on endpoint
}