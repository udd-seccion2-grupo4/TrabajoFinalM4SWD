import { Form, Input, Button, Row, Col, Space, Descriptions } from 'antd';
import Paragraph from 'antd/lib/typography/Paragraph';
import Title from 'antd/lib/typography/Title';
import { useState } from 'react';

export const App = () => {

  const [isLoading, setLoading] = useState(false);
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);

  const onFinish = (values) => {
    setLoading(true);
    setResponse(null);
    setError(null);
    fetch('/rest/msdxc/dxc?sueldo=10&ahorro=10000')
      .then(response => {
        if (!response.ok) {
          return Promise.reject(response.status);
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        setResponse(data);
        setLoading(false);
      }).catch(error => {
        console.error(error);
        setError(error);
        setLoading(false);
      });
  };

  return <>
    <Row>
      <Col span={12} offset={6}>
        <Space direction='vertical' style={{ marginTop: 20, width: '100%' }} >
          <Title>Consulta tu 10 %</Title>
          <Form name="consultar" layout='vertical'
            onFinish={onFinish}
            autoComplete="off">

            <Form.Item label="Sueldo" name="sueldo"
              rules={[{ required: true, message: 'Debe ingresar el sueldo' }]} >
              <Input size='large' placeholder='Ingresar Sueldo' type={'number'} />
            </Form.Item>

            <Form.Item label="Ahorro" name="ahorro"
              rules={[{ required: true, message: 'Debe ingresar el ahorro.' }]} >
              <Input size='large' placeholder='Ingresar Ahorro' type={'number'} />
            </Form.Item>

            <Form.Item>
              <Button size='large' type="primary" loading={isLoading} htmlType="submit">Consultar 10%</Button>
            </Form.Item>

          </Form>
        </Space>
      </Col>
      {response ?
        <Col span={12} offset={6}>
          <Space direction='vertical' style={{ marginTop: 20, marginBottom: 20, width: '100%' }} >
            <Descriptions
              bordered
              title="Resultado"
            >
              <Descriptions.Item label="10 %">$ {Intl.NumberFormat('es-CL').format(response.dxc)}</Descriptions.Item>
              <Descriptions.Item label="Saldo">$ {Intl.NumberFormat('es-CL').format(response.saldo)}</Descriptions.Item>
              <Descriptions.Item label="Impuesto">$ {Intl.NumberFormat('es-CL').format(response.impuesto)}</Descriptions.Item>
              <Descriptions.Item label="Sueldo">$ {Intl.NumberFormat('es-CL').format(response.sueldo)}</Descriptions.Item>
              <Descriptions.Item label="Ahorro">$ {Intl.NumberFormat('es-CL').format(response.ahorro)}</Descriptions.Item>
            </Descriptions>
          </Space>
        </Col>
        : null}
      {error ?
        <Col span={12} offset={6}>
          <Paragraph type='danger'>Ha ocurrido un error al realizar la consulta: {error}</Paragraph>
        </Col>
        : null}
    </Row>
  </>
}