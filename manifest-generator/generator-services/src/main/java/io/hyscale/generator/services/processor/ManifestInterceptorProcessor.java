/**
 * Copyright 2019 Pramati Prism, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.hyscale.generator.services.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.hyscale.commons.component.IInterceptorProcessor;
import io.hyscale.commons.exception.CommonErrorCode;
import io.hyscale.commons.exception.HyscaleException;
import io.hyscale.commons.models.ManifestContext;
import io.hyscale.servicespec.commons.model.service.ServiceSpec;

public abstract class ManifestInterceptorProcessor implements IInterceptorProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ManifestInterceptorProcessor.class);

    private static final String COMPONENT = "Manifest Generator";

    protected abstract void _preProcess(ServiceSpec serviceSpec, ManifestContext context) throws HyscaleException;

    protected abstract void _postProcess(ServiceSpec serviceSpec, ManifestContext context) throws HyscaleException;

    protected abstract void _onError(ServiceSpec serviceSpec, ManifestContext context) throws HyscaleException;

    @Override
    public void preProcess(Object... args) throws HyscaleException {
        if (!isInputValid(args)) {
            HyscaleException ex = new HyscaleException(CommonErrorCode.FAILED_TO_EXECUTE_PROCESSOR,
                    getFailureMsg(COMPONENT, "pre processing"));
            logger.error(ex.getMessage());
            throw ex;
        }
        _preProcess((ServiceSpec) args[0], (ManifestContext) args[1]);
    }

    @Override
    public void postProcess(Object... args) throws HyscaleException {
        if (!isInputValid(args)) {
            HyscaleException ex = new HyscaleException(CommonErrorCode.FAILED_TO_EXECUTE_PROCESSOR,
                    getFailureMsg(COMPONENT, "post processing"));
            logger.error(ex.getMessage());
            throw ex;
        }
        _postProcess((ServiceSpec) args[0], (ManifestContext) args[1]);
    }

    @Override
    public void onError(Object... args) throws HyscaleException {
        if (!isInputValid(args)) {
            HyscaleException ex = new HyscaleException(CommonErrorCode.FAILED_TO_EXECUTE_PROCESSOR,
                    getFailureMsg(COMPONENT, "error processing"));
            logger.error(ex.getMessage());
            throw ex;
        }
        _onError((ServiceSpec) args[0], (ManifestContext) args[1]);
    }

    private boolean isInputValid(Object... args) {
        if (args == null || args.length < 2) {
            return false;
        }
        if (args[0] == null || args[1] == null) {
            return false;
        }
        if (!(args[0] instanceof ServiceSpec) || !(args[1] instanceof ManifestContext)) {
            return false;
        }
        return true;
    }
}